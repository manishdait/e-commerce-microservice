package com.example.order.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.order.orderline.OrderLine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_order")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_generator")
  @SequenceGenerator(name = "order_seq_generator", sequenceName = "order_seq", allocationSize = 1, initialValue = 101)
  private Long id;
  private String reference;
  private BigDecimal totalAmount;
  @Enumerated(value = EnumType.STRING)
  private PaymentMethod paymentMethod;
  private String customerId;

  @OneToMany(mappedBy = "order")
  private List<OrderLine> orderLines;
  
  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdDate;

  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime modifiedDate;
}
