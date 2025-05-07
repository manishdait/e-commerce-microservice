package com.example.customer.customer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.customer.exception.CustomerNotFoundException;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository repository;
  private final CustomerMapper mapper;

  public String createCustomer(CustomerRequest request) {
    Customer customer = repository.save(mapper.toCustomer(request));
    return customer.getId();
  }

  public void updateCustomer(CustomerRequest request) {
    Customer customer = repository.findById(request.id()).orElseThrow(
      () -> new CustomerNotFoundException("Customer not found")
    );

    mergeCustomer(customer, request);
    repository.save(customer);
  }

  private void mergeCustomer(Customer customer, CustomerRequest request) {
    if(StringUtils.isNotBlank(request.firstName())) {
      customer.setFirstName(request.firstName());
    }
    if(StringUtils.isNotBlank(request.lastName())) {
      customer.setLastName(request.lastName());
    }
    if(StringUtils.isNotBlank(request.email())) {
      customer.setEmail(request.email());
    }
    if(request.address() != null) {
      customer.setAddress(request.address());
    }
  }

  public List<CustomerResponse> findAllCutomers() {
    return repository.findAll().stream()
      .map(c -> mapper.toCustomerResponse(c))
      .toList();
  }

  public CustomerResponse findCustomerById(String customerId) {
    Customer customer = repository.findById(customerId).orElseThrow(
      () -> new CustomerNotFoundException("Customer not found")
    );

    return mapper.toCustomerResponse(customer);
  }

  public Boolean existsById(String customerId) {
    return repository.findById(customerId).isPresent();
  }

  public void deleteCustomerById(String customerId) {
    repository.deleteById(customerId);
  }
}
