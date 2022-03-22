package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "ordered_product",indexes = {@Index(name = "ix_cafeIdAndTableId", columnList = "cafeId,tableId",unique = true)})
public class OrderedProduct
{
  @Id
  private int id;

  private int cafeId;

  private int tableId;

  private int productId;
}
