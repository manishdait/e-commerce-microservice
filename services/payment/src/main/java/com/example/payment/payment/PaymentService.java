package com.example.payment.payment;

import org.springframework.stereotype.Service;

import com.example.payment.notification.NotificationProducer;
import com.example.payment.notification.PaymentNotificationRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
  private final PaymentRepository repository;
  private final PaymentMapper mapper;

  private NotificationProducer notificationProducer;

  public Long createPayment(PaymentRequest request) {
    Payment payment = mapper.toPayment(request);

    notificationProducer.sendNotification(
      new PaymentNotificationRequest(
        request.orderReference(), 
        request.amount(), 
        request.paymentMethod(), 
        request.customer().firstName(), 
        request.customer().lastName(), 
        request.customer().email()
      )
    );

    return repository.save(payment).getId();
  }
}
