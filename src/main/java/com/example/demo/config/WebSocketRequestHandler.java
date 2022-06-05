package com.example.demo.config;

import com.example.demo.components.*;
import com.example.demo.entities.Order;
import com.example.demo.security.User;
import com.example.demo.vo.SocketCommand;
import com.example.demo.vo.SocketMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.util.List;

public class WebSocketRequestHandler extends BinaryWebSocketHandler
{
  @Autowired
  protected ApplicationEventPublisher dispatcher;

  @Autowired
  private SocketSessionService socketSessionService;

  @Autowired
  private KitchenService kitchenService;

  @Autowired
  private OrderService orderService;

  @Autowired
  private WaiterService waiterService;

  @Autowired
  private AuthService authService;

  @Autowired
  private BusinessService businessService;

  @Override
  public void afterConnectionEstablished(WebSocketSession session)
  {
    System.out.println(session.getId());
  }

  @Override
  protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message)
  {
  }

  @Override
  public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception
  {
    SocketMessageVo socketMessageVo = SocketMessageVo.parse((TextMessage) message);
    System.out.println(session.getId());

    if (socketMessageVo.getCommand().equals(SocketCommand.OPENWAITER))
    {
      User user = authService.tokenUserMap.get(socketMessageVo.getToken());
      waiterService.putSession(user.getId(), session);
    } else if(socketMessageVo.getCommand().equals(SocketCommand.OPENKITCHEN)){
      User user = authService.tokenUserMap.get(socketMessageVo.getToken());
      kitchenService.kitchenMap.put(user.getBusinessId(), session);
    }else if(socketMessageVo.getCommand().equals(SocketCommand.SEATTABLE)){
      businessService.tableMap.get(socketMessageVo.getCafeId()).put(socketMessageVo.getTableId(),true);
    }

    if (socketMessageVo.getCommand().equals(SocketCommand.OPENCAFE))
    {
      socketSessionService.addCafe(socketMessageVo.getCafeId(), session);
    } else if (socketMessageVo.getCommand().equals(SocketCommand.CLOSECAFE))
    {
      socketSessionService.removeCafe(socketMessageVo.getCafeId());

    }else if(socketMessageVo.getCommand().equals(SocketCommand.MAKEORDER)){
      socketSessionService.getCafeById(socketMessageVo.getCafeId()).sendMessage(new TextMessage("tableId:"+socketMessageVo.getTableId()));

    }else if(socketMessageVo.getCommand().equals(SocketCommand.ORDERREADY)){
      User user = authService.tokenUserMap.get(socketMessageVo.getToken());
      kitchenService.sendReadyNotification(user.getBusinessId(), socketMessageVo.getTableId());

      int orderId = socketMessageVo.getOrderId();

      List<Order> orders = orderService.orderMap.get(user.getBusinessId()).get(socketMessageVo.getTableId());

      for (Order order : orders) {
        if(order.getId() == orderId){
          order.setServed(true);
          break;
        }
      }


    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
  {
    socketSessionService.removeCafe(session);
  }
}
