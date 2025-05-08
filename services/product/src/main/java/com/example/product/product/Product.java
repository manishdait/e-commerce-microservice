package com.example.product.product;

import java.math.BigDecimal;

import com.example.product.category.Category;

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
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_generator")
  @SequenceGenerator(name = "product_seq_generator", sequenceName = "product_seq", allocationSize = 1, initialValue = 101)
  private Long id;
  private String name;
  private String description;
  private Double availableQuantity;
  private BigDecimal price;
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
}
