package com.example.customer.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
  public Customer toCustomer(CustomerRequest request) {
    return Customer.builder()
      .firstName(request.firstName())
      .lastName(request.lastName())
      .email(request.email())
      .address(request.address())
      .build();
  }

  public CustomerResponse toCustomerResponse(Customer customer) {
    return new CustomerResponse(
      customer.getId(),
      customer.getFirstName(),
      customer.getLastName(),
      customer.getEmail(),
      customer.getAddress());
  }
}
