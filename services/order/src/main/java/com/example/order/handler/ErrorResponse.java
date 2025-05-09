package com.example.order.handler;

import java.time.Instant;

public record ErrorResponse<T>(
  Instant timestamp,
  Integer status,
  T message,
  String path
) {
  
}
