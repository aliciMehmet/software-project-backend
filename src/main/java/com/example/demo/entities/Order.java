package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class Order {
    private Date orderDate;

    private double totalPrice;

    private List<Integer> itemIdList;

    private int tableId;

    private boolean isServed;
}
