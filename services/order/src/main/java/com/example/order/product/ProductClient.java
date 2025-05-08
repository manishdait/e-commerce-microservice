package com.example.order.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.order.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductClient {
  @Value("${application.config.product-url}")
  private String productUrl;

  private final RestTemplate restTemplate;

  public List<PurchaseResponse> purchasedProduct(List<PurchaseRequest> purchaseRequests) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

    ResponseEntity<List<PurchaseResponse>> response = restTemplate.exchange(
      productUrl + "/purchase",
      HttpMethod.POST,
      new HttpEntity<>(purchaseRequests, headers),
      new ParameterizedTypeReference<List<PurchaseResponse>>(){}
    );

    if (response.getStatusCode().isError()) {
      throw new BusinessException("Error occur while purchasing product: " + response.getStatusCode());
    }

    return response.getBody();
  } 
}
