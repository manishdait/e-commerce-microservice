package com.example.order.order;

import java.math.BigDecimal;
import java.util.List;

import com.example.order.product.PurchaseRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
  Long id,
  
  String reference,
  
  @Positive(message = "Order amount must be positive")
  BigDecimal totalAmount,
  
  @NotNull(message = "Order payment-method should not be null")
  PaymentMethod paymentMethod,

  @NotNull(message = "CUstomer must be present")
  @NotEmpty(message = "CUstomer must be present")
  @NotBlank(message = "CUstomer must be present")
  String customerId,
  
  @NotEmpty(message = "Products must not be empty")
  List<PurchaseRequest> products
) {

}
