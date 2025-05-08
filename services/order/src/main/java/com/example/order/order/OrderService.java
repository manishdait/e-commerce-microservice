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
import com.example.order.product.ProductClient;
import com.example.order.product.PurchaseRequest;
import com.example.order.product.PurchaseResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final OrderMapper mapper;

  private final CustomerClient customerClient;
  private final ProductClient productClient;

  private final OrderLineService orderLineService;
  private final OrderProducer orderProducer;

  public Long cerateOrder(OrderRequest request) {
    CustomerResponse customer = customerClient.findCustomerById(request.customerId()).orElseThrow(
      () -> new BusinessException("Can not create order: Customer not exists")
    );

    List<PurchaseResponse> purchaseProduct = productClient.purchasedProduct(request.products());

    Order order = mapper.toOrder(request);
    orderRepository.save(order);

    for (PurchaseRequest purchaseRequest : request.products()) {
      orderLineService.saveOrderLine(new OrderLineRequest(
        null, 
        order.getId(), 
        purchaseRequest.productId(), 
        purchaseRequest.quantity())
      );
    }

    // TODO: process-payment

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
}
