package com.example.order.orderline;

import org.springframework.stereotype.Component;

import com.example.order.order.Order;

@Component
public class OrderLineMapper {
  public OrderLine toOrderLine(OrderLineRequest request) {
    return OrderLine.builder()
      .id(request.id())
      .order(Order.builder().id(request.id()).build())
      .productId(request.productId())
      .quantity(request.quantity())
      .build();
  } 
}
