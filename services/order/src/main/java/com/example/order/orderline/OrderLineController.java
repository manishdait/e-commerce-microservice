package com.example.order.orderline;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order-line")
@RequiredArgsConstructor
public class OrderLineController {
  private final OrderLineService service;

  @GetMapping("/{orderId}")
  public ResponseEntity<List<OrderLineResponse>> getOrderLineByOrderId(@PathVariable Long orderId) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getOrderLineByOrderId(orderId));
  }
}
