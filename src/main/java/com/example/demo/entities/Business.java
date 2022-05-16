package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class Business {
    @Id
    private int id;

    private String name;

    private String userName;

    private String password;

}
