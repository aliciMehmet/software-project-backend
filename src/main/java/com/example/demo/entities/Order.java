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
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private Date orderDate;

    private double totalPrice;

    @ElementCollection
    private List<Integer> itemIdList;

    private int tableId;

    private boolean isServed;
}
