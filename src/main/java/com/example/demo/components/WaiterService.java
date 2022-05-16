package com.example.demo.components;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WaiterService {
    Map<Integer,Map<Integer,Boolean>> waiterMap = new HashMap<>();

    public void acceptOrder(int orderId){

    }

    public void completeOrder(int orderId){

    }

    public void finishCallRequest(){

    }
}
