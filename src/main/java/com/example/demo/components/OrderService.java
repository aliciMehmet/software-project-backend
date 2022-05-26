package com.example.demo.components;

import com.example.demo.entities.Item;
import com.example.demo.entities.Order;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderService {
    @Autowired
    private ItemService itemService;

    private OrderRepository orderRepository;

    public Map<Integer,Map<Integer, List<Order>>> orderMap = new HashMap<>();

    public  void placeOrder(List<Integer> itemList,int businessId,int tableId){

        int totalPrice = 0;
        for (Item item : itemService.getAllItems(businessId)) {
            if(itemList.contains(item.getId())){
                totalPrice += item.getPrice();
            }
        }
        Order order = new Order();

        order.setServed(false);
        order.setItemIdList(itemList);
        order.setTableId(tableId);
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        orderMap.get(businessId).get(tableId).add(order);
    }

    public List<Order> getReceipt(int businessId,int tableId){

        return orderMap.get(businessId).get(tableId);
    }

    public void completePayment(int businessId,int tableId){
        orderMap.get(businessId).put(tableId,new ArrayList<>());
    }




}
