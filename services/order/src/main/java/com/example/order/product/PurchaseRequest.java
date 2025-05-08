package com.example.order.product;

import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
  Long productId,
  @Positive(message = "Product quantity must be positive")
  double quantity
) {
  
}
