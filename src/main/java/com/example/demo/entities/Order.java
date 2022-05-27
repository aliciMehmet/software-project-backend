package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double totalPrice;

    private int itemId;

    @Transient
    private String itemName;

    private int count;

    private int businessId;

    private int tableId;

    @Transient
    private boolean isServed;
}
