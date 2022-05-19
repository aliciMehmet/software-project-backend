package com.example.demo.components;

import com.example.demo.vo.SocketMessageVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class KitchenService {

    @Autowired
    private WaiterService waiterService;

    public Map<Integer, WebSocketSession> kitchenMap = new HashMap<>();

    public void sendReadyNotification(int businessId,int tableId) throws IOException {

        Map<Integer, Boolean> waiters = waiterService.waiterMap.get(businessId);

        if(waiters != null){
            for (Map.Entry<Integer, Boolean> pair : waiters.entrySet()) {
                if(pair.getValue()){
                    waiterService.socketSessionMap.get(pair.getKey()).sendMessage(new TextMessage("hello coni"));
                }//new TextMessage("dsfdsf")
            }
        }

    }

    public void sendNewOrderNotification(int businessId,String itemName,int count) throws IOException {
        String str = "{\n" +
                "  \"command\":\"NEWORDER\",\n" +
                "  \"itemName\":\""+itemName+"\",\n" +
                "  \"count\":"+count+"\n" +
                "}";
        kitchenMap.get(businessId).sendMessage(new TextMessage(str));
    }
}
