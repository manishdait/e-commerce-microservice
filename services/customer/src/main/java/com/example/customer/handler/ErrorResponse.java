package com.example.customer.handler;

import java.time.Instant;

public record ErrorResponse<T>(
  Instant timestamp,
  Integer status,
  T message,
  String path
) {
  
}
