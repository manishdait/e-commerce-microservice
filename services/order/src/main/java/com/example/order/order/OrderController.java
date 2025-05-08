package com.example.order.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService service;

  public ResponseEntity<Long> createOrder(@RequestBody @Valid OrderRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.cerateOrder(request));
  }
}
