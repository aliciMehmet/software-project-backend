package com.example.demo.components;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Component
public class WaiterService {
    Map<Integer,Map<Integer,Boolean>> waiterMap = new HashMap<>();

    Map<Integer, WebSocketSession> socketSessionMap = new HashMap<>();


    public void acceptOrder(int orderId){

    }

    public void completeOrder(int orderId){

    }

    public void finishCallRequest(){

    }

    public void loginWaiter(int businessId,int waiterId){

        if(!waiterMap.containsKey(businessId)){
            waiterMap.put(businessId,new HashMap<>());
        }

        waiterMap.get(businessId).put(waiterId,true);

    }

    public void putSession(int waiterId, WebSocketSession session){
        socketSessionMap.put(waiterId,session);
    }
}
