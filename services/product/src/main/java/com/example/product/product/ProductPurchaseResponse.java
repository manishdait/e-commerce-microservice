package com.example.product.product;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
  Long productId,
  String name,
  String description,
  BigDecimal price,
  double quantity
)  {

}
