package com.example.product.product;

import java.math.BigDecimal;

public record ProductResponse(
  Long id,
  String name,
  String description,
  Double availableQuantity,
  BigDecimal price,
  Long categoryId,
  String categoryName,
  String categoryDescription
)  {

}
