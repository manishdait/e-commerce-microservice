package com.example.order.handler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.order.exception.BusinessException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse<String>> handle(BusinessException e, HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
      new ErrorResponse<String>(
        Instant.now(),
        HttpStatus.NOT_FOUND.value(),
        e.getMessage(),
        request.getRequestURI()
      )
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse<Map<String, String>>> handle(MethodArgumentNotValidException e, HttpServletRequest request) {
    Map<String, String> errors = new HashMap<>();
    e.getBindingResult().getAllErrors().forEach(
      error -> {
        String field = ((FieldError) error).getField();
        String message = error.getDefaultMessage();
        errors.put(field, message);
      }
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
      new ErrorResponse<Map<String, String>>(
        Instant.now(),
        HttpStatus.BAD_REQUEST.value(),
        errors,
        request.getRequestURI()
      )
    );
  }
}
