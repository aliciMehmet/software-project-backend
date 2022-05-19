package com.example.demo.repositories;

import com.example.demo.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer>
{
  List<Item> getByBusinessId(int cafeId);

  List<Item> getByBusinessIdAndCategory(int cafeId, String category);
}
