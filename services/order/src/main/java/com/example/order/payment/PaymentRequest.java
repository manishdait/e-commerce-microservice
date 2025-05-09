package com.example.order.payment;

import java.math.BigDecimal;

import com.example.order.customer.CustomerResponse;
import com.example.order.order.PaymentMethod;

public record PaymentRequest(
  BigDecimal amount,
  PaymentMethod paymentMethod,
  String orderReference,
  Long orderId,
  CustomerResponse customer
)  {

}
