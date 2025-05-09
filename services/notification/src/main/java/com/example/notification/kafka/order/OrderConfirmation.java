package com.example.notification.kafka.order;

import java.math.BigDecimal;
import java.util.List;

import com.example.notification.kafka.customer.Customer;
import com.example.notification.kafka.payment.PaymentMethod;

public record OrderConfirmation(
  String orderReference,
  BigDecimal totalAmount,
  PaymentMethod paymentMethod,
  Customer customer,
  List<Product> products
) {

}
