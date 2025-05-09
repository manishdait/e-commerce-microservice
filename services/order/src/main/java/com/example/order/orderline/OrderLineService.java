package com.example.order.orderline;

import java.util.List;

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

  public List<OrderLineResponse> getOrderLineByOrderId(Long orderId) {
    return repository.findAllByOrderId(orderId).stream()
      .map(o -> mapper.toOrderLineResponse(o))
      .toList();
  } 
}
