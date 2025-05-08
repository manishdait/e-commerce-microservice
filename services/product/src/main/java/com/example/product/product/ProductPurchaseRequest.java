package com.example.product.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
  @NotNull(message = "Product id required")
  Long productId,
  @Positive(message = "Product quantity must be positive")
  double quantity
)  {

}
