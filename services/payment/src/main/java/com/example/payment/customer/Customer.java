package com.example.payment.customer;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Validated
public record Customer(
  String id,
  @NotNull(message = "Customer firstname required")
  String firstName,
  @NotNull(message = "Customer lastname required")
  String lastName,
  @NotNull(message = "Customer email required")
  @Email(message = "Customer email address must be valid")
  String email
) {

}
