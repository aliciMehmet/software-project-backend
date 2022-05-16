package com.example.demo.repositories;

import com.example.demo.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table,Integer> {

}
