package com.example.demo.repositories;

import com.example.demo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> getAllByBusinessId(int businessId);
}
