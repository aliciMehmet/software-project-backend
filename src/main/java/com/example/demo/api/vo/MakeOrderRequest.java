package com.example.demo.api.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakeOrderRequest {
    private int businessId;
    private String itemName;
    private int count;
}
