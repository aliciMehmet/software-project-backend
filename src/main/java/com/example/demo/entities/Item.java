package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Item
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int businessId;

  private String name;

  private String category;

  private double price;

  private int stock;

  private int score;

  @CreationTimestamp
  private Timestamp createdAt;

  @UpdateTimestamp
  private Timestamp updatedAt;

}
