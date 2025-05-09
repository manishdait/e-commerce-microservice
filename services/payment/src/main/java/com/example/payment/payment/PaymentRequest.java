package com.example.payment.payment;

import java.math.BigDecimal;

import com.example.payment.customer.Customer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PaymentRequest(
  Long id,
  @Positive(message = "Payment amount must be positive")
  BigDecimal amount,
  @NotNull(message = "Payment method must not be null")
  PaymentMethod paymentMethod,
  @NotNull(message = "OrderId required for creating payment")
  Long orderId,
  @NotNull(message = "Order reference must not be null")
  String orderReference,
  Customer customer
) {

}
