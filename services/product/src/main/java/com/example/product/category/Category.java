package com.example.product.category;

import java.util.List;

import com.example.product.product.Product;

import jakarta.persistence.Entity;
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
@Table(name = "category")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq_generator")
  @SequenceGenerator(name = "category_seq_generator", sequenceName = "category_seq", allocationSize = 1, initialValue = 101)
  private Long id;
  private String name;
  private String description;
  @OneToMany(mappedBy = "category")
  private List<Product> products;
}
