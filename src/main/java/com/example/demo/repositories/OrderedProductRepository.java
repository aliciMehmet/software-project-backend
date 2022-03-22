package com.example.demo.repositories;

import com.example.demo.entities.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Integer>
{
  public List<OrderedProduct> getByCafeIdAndTableId(int cafeId, int tableId);
}
