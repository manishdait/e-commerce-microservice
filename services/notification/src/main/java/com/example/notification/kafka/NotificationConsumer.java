package com.example.notification.kafka;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.notification.kafka.order.OrderConfirmation;
import com.example.notification.kafka.payment.PaymentConfirmation;
import com.example.notification.notification.Notification;
import com.example.notification.notification.NotificationRepository;
import com.example.notification.notification.NotificationType;
import com.example.notification.notification.email.EmailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
  private final NotificationRepository repository;
  private final EmailService service;

  @KafkaListener(topics = {"payment-topic"})
  public void consumePaymentSucessfulNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
    log.info(String.format("Consuming message from payment topic :: %d", paymentConfirmation));
    repository.save(
      Notification.builder()
        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
        .notificationDate(LocalDateTime.now())
        .paymentConfirmation(paymentConfirmation)
        .build()
    );

    String customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
    service.sendPaymentSucessEmail(
      paymentConfirmation.customerEmail(), 
      customerName, 
      paymentConfirmation.amount(), 
      paymentConfirmation.orderReference()
    );
  }

  @KafkaListener(topics = {"order-topic"})
  public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
    log.info(String.format("Consuming message from order topic :: %d", orderConfirmation));
    repository.save(
      Notification.builder()
        .notificationType(NotificationType.ORDER_CONFIRMATION)
        .notificationDate(LocalDateTime.now())
        .orderConfirmation(orderConfirmation)
        .build()
    );

    String customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().firstName();
    service.sendOrderConfirmationEmail(
      orderConfirmation.customer().email(), 
      customerName, 
      orderConfirmation.totalAmount(), 
      orderConfirmation.orderReference(),
      orderConfirmation.products()
    );
  }
}
