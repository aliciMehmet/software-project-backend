package com.example.demo.api;

import com.example.demo.entities.Item;
import com.example.demo.components.ItemService;
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
  private ItemService itemService;

  @GetMapping("/getAllProducts")
  public List<Item> getAllProducts(@RequestParam int cafeId)
  {
    return itemService.getAllItems(cafeId);
  }

  @GetMapping("/getProductsByCategory")
  public List<Item> getAllProductsByCategory(@RequestParam int cafeId, @RequestParam String category)
  {
    return productRepository.getByBusinessIdAndCategory(cafeId, category);
  }

}