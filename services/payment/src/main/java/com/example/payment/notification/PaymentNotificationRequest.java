package com.example.payment.notification;

import java.math.BigDecimal;

import com.example.payment.payment.PaymentMethod;

public record PaymentNotificationRequest(
  String orderReference,
  BigDecimal amount,
  PaymentMethod paymentMethod,
  String customerFirstname,
  String customerLastname,
  String customerEmail
) {

}
