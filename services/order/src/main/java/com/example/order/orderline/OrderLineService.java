package com.example.order.orderline;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderLineService {
  private final OrderLineRepository repository;
  private final OrderLineMapper mapper;

  public Long saveOrderLine(OrderLineRequest request) {
    OrderLine orderLine = mapper.toOrderLine(request);
    return repository.save(orderLine).getId();
  } 
}
