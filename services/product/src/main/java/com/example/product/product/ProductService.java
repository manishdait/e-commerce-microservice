package com.example.product.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.product.exception.ProductPurchaseException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository repository;
  private final ProductMapper mapper;

  public Long createProduct(ProductRequest request) {
    Product product = mapper.toProduct(request);
    return repository.save(product).getId();
  }

  public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {
    List<Long> productIds = request.stream().map(r -> r.productId()).toList();
    List<Product> storedProducts = repository.findAllByIdInOrderById(productIds);

    if (storedProducts.size() != productIds.size()) {
      throw new ProductPurchaseException("One or more  prduct not found");
    }

    List<ProductPurchaseRequest> storedRequest = request.stream()
      .sorted((p1, p2) -> p1.productId().compareTo(p2.productId()))
      .toList();

    List<ProductPurchaseResponse> purchaseProduct = new ArrayList<>();

    for (int i = 0; i < storedProducts.size(); i++) {
      Product product = storedProducts.get(i);
      ProductPurchaseRequest purchaseRequest = storedRequest.get(i);

      if (purchaseRequest.quantity() > product.getAvailableQuantity()) {
        throw new ProductPurchaseException("Insufficent peoduct quantity");
      }

      double newQuantity = product.getAvailableQuantity() - purchaseRequest.quantity();
      product.setAvailableQuantity(newQuantity);
      repository.save(product);

      purchaseProduct.add(mapper.toProductPurchaseResponse(product, purchaseRequest.quantity()));
    }

    return purchaseProduct;
  }

  public List<ProductResponse> getAllProducts() {
    return repository.findAll().stream()
      .map(p -> mapper.toProductResponse(p))
      .toList();
  }

  public ProductResponse getProductById(Long id) {
    Product product = repository.findById(id).orElseThrow(
      () -> new EntityNotFoundException("Product not found")
    );
    return mapper.toProductResponse(product);
  }
}
