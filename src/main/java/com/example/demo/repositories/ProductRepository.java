package com.example.demo.repositories;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>
{
  List<Product> getByCafeId(int cafeId);

  List<Product> getByCafeIdAndType(int cafeId, String type);
}
