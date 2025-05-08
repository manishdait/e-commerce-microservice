package com.example.order.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.example.order.customer.CustomerResponse;
import com.example.order.order.PaymentMethod;
import com.example.order.product.PurchaseResponse;

public record OrderConfirmation(
  String orderReference,
  BigDecimal totalAmount,
  PaymentMethod paymentMethod,
  CustomerResponse customer,
  List<PurchaseResponse> products
) {

}
