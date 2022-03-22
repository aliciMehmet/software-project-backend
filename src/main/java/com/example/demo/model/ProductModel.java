package com.example.demo.model;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProductModel extends ReloadModel
{
  //key: cafeId, value: products of Cafe
  private Map<Integer, List<Product>> cafeMap;

  @Autowired
  private ProductRepository productRepository;

  @PostConstruct
  public void started()
  {
    cafeMap = new ConcurrentHashMap<>();
  }

  @Override
  public void reload()
  {
    List<Product> products = productRepository.findAll();

    for (Product product : products)
    {
      if (!cafeMap.containsKey(product.getCafeId()))
      {
        cafeMap.put(product.getCafeId(), new ArrayList<>());
      }
      cafeMap.get(product.getCafeId()).add(product);
    }
  }
}
