package com.example.order.orderline;

public record OrderLineRequest(
  Long id,
  Long orderId,
  Long productId,
  double quantity
) {
  
}
