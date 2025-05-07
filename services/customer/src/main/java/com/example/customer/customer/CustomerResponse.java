package com.example.customer.customer;

import com.example.customer.address.Address;

public record CustomerResponse(
  String id,
  String firstName,
  String lastName,
  String email,
  Address address
) {

}
