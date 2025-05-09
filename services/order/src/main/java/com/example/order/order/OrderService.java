package com.example.order.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.order.customer.CustomerClient;
import com.example.order.customer.CustomerResponse;
import com.example.order.exception.BusinessException;
import com.example.order.kafka.OrderConfirmation;
import com.example.order.kafka.OrderProducer;
import com.example.order.orderline.OrderLineRequest;
import com.example.order.orderline.OrderLineService;
import com.example.order.payment.PaymentClient;
import com.example.order.payment.PaymentRequest;
import com.example.order.product.ProductClient;
import com.example.order.product.PurchaseRequest;
import com.example.order.product.PurchaseResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository repository;
  private final OrderMapper mapper;

  private final CustomerClient customerClient;
  private final ProductClient productClient;

  private final OrderLineService orderLineService;
  private final OrderProducer orderProducer;
  private final PaymentClient paymentClient;

  public Long cerateOrder(OrderRequest request) {
    CustomerResponse customer = customerClient.findCustomerById(request.customerId()).orElseThrow(
      () -> new BusinessException("Can not create order: Customer not exists")
    );

    List<PurchaseResponse> purchaseProduct = productClient.purchasedProduct(request.products());

    Order order = mapper.toOrder(request);
    repository.save(order);

    for (PurchaseRequest purchaseRequest : request.products()) {
      orderLineService.saveOrderLine(new OrderLineRequest(
        null, 
        order.getId(), 
        purchaseRequest.productId(), 
        purchaseRequest.quantity())
      );
    }

    paymentClient.createOrderPayment(
      new PaymentRequest(
        request.totalAmount(), 
        request.paymentMethod(), 
        request.reference(), 
        order.getId(),
        customer
      )
    );

    orderProducer.sendOrderConfirmation(
      new OrderConfirmation(
        request.reference(), 
        request.totalAmount(), 
        request.paymentMethod(), 
        customer, 
        purchaseProduct
      )
    );

    return order.getId();
  }

  public List<OrderResponse> getAllOrders() {
    return repository.findAll().stream()
      .map(o -> mapper.toOrderResponse(o))
      .toList();
  }

  public OrderResponse getOrderById(Long orderId) {
    Order order = repository.findById(orderId).orElseThrow(
      () -> new EntityNotFoundException("Order not found")
    );
    
    return mapper.toOrderResponse(order);
  }
}
