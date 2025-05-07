package com.example.customer.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService service;

  @PostMapping()
  public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.createCustomer(request));
  }

  @PutMapping()
  public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {
    service.updateCustomer(request);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
  }

  @GetMapping()
  public ResponseEntity<List<CustomerResponse>> findAllCutomers() {
    return ResponseEntity.status(HttpStatus.OK).body(service.findAllCutomers());
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable String customerId) {
    return ResponseEntity.status(HttpStatus.OK).body(service.findCustomerById(customerId));
  }

  @GetMapping("/exists/{customerId}")
  public ResponseEntity<Boolean> existsById(@PathVariable String customerId) {
    return ResponseEntity.status(HttpStatus.OK).body(service.existsById(customerId));
  }

  @DeleteMapping("/{customerId}")
  public ResponseEntity<Void> deleteCustomerById(@PathVariable String customerId) {
    service.deleteCustomerById(customerId);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

}
