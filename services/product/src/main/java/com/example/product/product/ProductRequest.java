package com.example.product.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
  @NotNull(message = "Product name required")
  String name,
  @NotNull(message = "Product description required")
  String description,
  @Positive(message = "Product quantity must be positive")
  Double availableQuantity,
  @Positive(message = "Product price must be positive")
  BigDecimal price,
  @NotNull(message = "Product category required")
  Long categoryId
)  {

}
