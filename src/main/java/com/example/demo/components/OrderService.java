package com.example.demo.components;

import com.example.demo.entities.Item;
import com.example.demo.entities.Order;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderService {
    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderRepository orderRepository;

    public Map<Integer,Map<Integer, List<Order>>> orderMap = new HashMap<>();

    @PostConstruct
    public void started()
    {

    }

    public  void placeOrder(int itemId,int businessId,int tableId,int count){

        double totalPrice = 0;
        String itemName = "";
        List<Item> allItems = itemService.getAllItems(businessId);

        for (Item item : allItems) {
            if(item.getId() == itemId){
                totalPrice = item.getPrice() * count;
                itemName = item.getName();
                break;
            }
        }

        Order order = new Order();

        order.setServed(false);
        order.setItemId(itemId);
        order.setCount(count);
        order.setBusinessId(businessId);
        order.setTableId(tableId);
        order.setTotalPrice(totalPrice);
        order.setItemName(itemName);
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
