package com.example.notification.notification.email;

import lombok.Getter;

public enum EmailTemplate {
  PAYMENT_CONFIRMATION("payment_confirmation.html", "Payment Sucessfull"),
  ORDER_CONFIRMATION("order_confirmation.html", "Order Confirmation");

  @Getter
  private String template;
  @Getter
  private String subject;

  EmailTemplate(String template, String subject) {
    this.template = template;
    this.subject = subject;
  }
}
