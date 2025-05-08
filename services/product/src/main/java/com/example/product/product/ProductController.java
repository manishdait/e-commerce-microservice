package com.example.product.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService service;

  @PostMapping()
  public ResponseEntity<Long> createProduct(@RequestBody @Valid ProductRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.createProduct(request));
  }

  @PostMapping("/purchase")
  public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody List<ProductPurchaseRequest> request) {
    return ResponseEntity.status(HttpStatus.OK).body(service.purchaseProduct(request));
  }

  @GetMapping()
  public ResponseEntity<List<ProductResponse>> getAllProducts() {
    return ResponseEntity.status(HttpStatus.OK).body(service.getAllProducts());
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getProductById(id));
  }
}
