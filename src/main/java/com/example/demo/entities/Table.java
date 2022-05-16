package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "tbl")
public class Table {
    @Id
    private int id;

    private int tableId;

    private int businessId;
}
