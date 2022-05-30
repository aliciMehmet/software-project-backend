package com.example.demo.components;

import com.example.demo.api.vo.WaiterNotificationVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.Map;

@Component
public class TableService {

    @Autowired
    private WaiterService waiterService;

    public void callWaiter(int businessId, int tableId) throws IOException {
        Map<Integer, Boolean> waiters = waiterService.waiterMap.get(businessId);

        for (Map.Entry<Integer, Boolean> entry : waiters.entrySet()) {
            if(entry.getValue()){
                WaiterNotificationVo notificationVo = new WaiterNotificationVo();
                notificationVo.setCommand("CALL");
                notificationVo.setTableId(tableId);

                ObjectMapper objectMapper = new ObjectMapper();
                String str = objectMapper.writeValueAsString(notificationVo);
                waiterService.socketSessionMap.get(entry.getKey()).sendMessage(new TextMessage(str));
            }
        }
    }

    public void giveScore(int itemId, double score){

    }
}
