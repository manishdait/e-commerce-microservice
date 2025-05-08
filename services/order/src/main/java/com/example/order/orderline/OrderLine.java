package com.example.order.orderline;

import com.example.order.order.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "orderline")
public class OrderLine {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderline_seq_generator")
  @SequenceGenerator(name = "orderline_seq_generator", sequenceName = "orderline_seq", allocationSize = 1, initialValue = 101)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;
  private Long productId;
  private double quantity;
}
