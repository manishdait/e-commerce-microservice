package com.example.order.order;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  @PostMapping()
  public ResponseEntity<Long> createOrder(@RequestBody @Valid OrderRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.cerateOrder(request));
  }

  @GetMapping()
  public ResponseEntity<List<OrderResponse>> getAllOrders() {
    return ResponseEntity.status(HttpStatus.OK).body(service.getAllOrders());
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getOrderById(orderId));
  } 
}
