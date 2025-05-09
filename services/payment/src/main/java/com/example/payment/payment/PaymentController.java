package com.example.payment.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
  private final PaymentService service;

  @PostMapping()
  public ResponseEntity<Long> createPayment(@RequestBody @Valid PaymentRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.createPayment(request));
  }
}
