package com.example.payment.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "payment")
@EntityListeners(AuditingEntityListener.class)
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq_generator")
  @SequenceGenerator(name = "payment_seq_generator", sequenceName = "payment_seq", allocationSize = 1, initialValue = 101)
  private Long id;
  private BigDecimal amount;
  @Enumerated(value = EnumType.STRING)
  private PaymentMethod paymentMethod;
  private Long orderId;
  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdAt;
  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime lastModifedAt;
}
