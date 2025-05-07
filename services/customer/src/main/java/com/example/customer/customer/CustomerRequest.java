package com.example.customer.customer;

import com.example.customer.address.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
  String id,
  
  @NotNull(message = "Customer firstname must not be null")
  String firstName,
  
  @NotNull(message = "Customer firstname must not be null")
  String lastName,

  @NotNull(message = "Customer firstname must not be null")
  @Email(message = "Email must be valid email address")
  String email,

  Address address
) {
  
}
