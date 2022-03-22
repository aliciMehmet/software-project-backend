package com.example.demo.api;

import com.example.demo.entities.OrderedProduct;
import com.example.demo.entities.Product;
import com.example.demo.repositories.OrderedProductRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderedProductRepository orderedProductRepository;

  @GetMapping("/getAllProducts")
  public List<Product> getAllProducts(@RequestParam int cafeId)
  {
    return productRepository.getByCafeId(cafeId);
  }

  @GetMapping("/getProductsByType")
  public List<Product> getAllProductsByType(@RequestParam int cafeId, @RequestParam String type)
  {
    return productRepository.getByCafeIdAndType(cafeId, type);
  }

  @GetMapping("/getReceipt")
  public List<OrderedProduct> getReceipt(@RequestParam int cafeId, @RequestParam int tableId)
  {
    return orderedProductRepository.getByCafeIdAndTableId(cafeId, tableId);
  }
}
