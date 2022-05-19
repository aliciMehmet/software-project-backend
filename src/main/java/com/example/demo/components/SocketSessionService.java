package com.example.demo.components;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SocketSessionService
{
  private Map<Integer, WebSocketSession> mapByCafeId;

  @PostConstruct
  public void OnPostConstruct()
  {
    mapByCafeId = new ConcurrentHashMap<>();
  }

  public void addCafe(int cafeId, WebSocketSession webSocketSession)
  {
    mapByCafeId.put(cafeId, webSocketSession);
  }

  public void removeCafe(int cafeId)
  {
    mapByCafeId.remove(cafeId);
  }

  public void removeCafe(WebSocketSession webSocketSession)
  {
    mapByCafeId.entrySet()
            .removeIf(entry -> (webSocketSession.equals(entry.getValue())));
  }

  public WebSocketSession getCafeById(int cafeId)
  {
    return mapByCafeId.get(cafeId);
  }

  public void sendSocketMessage(WebSocketSession webSocketSession) throws IOException {
    webSocketSession.sendMessage(new TextMessage("asdasd"));
  }
}
