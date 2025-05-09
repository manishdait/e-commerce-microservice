package com.example.order.order;

import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

  public Order toOrder(OrderRequest request) {
    return Order.builder()
      .customerId(request.customerId())
      .paymentMethod(request.paymentMethod())
      .reference(request.reference())
      .totalAmount(request.totalAmount())
      .build();
  }

  public OrderResponse toOrderResponse(Order order) {
    return new OrderResponse(
      order.getId(), 
      order.getReference(), 
      order.getTotalAmount(), 
      order.getPaymentMethod(), 
      order.getCustomerId()
    );
  }
}
