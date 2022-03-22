package com.example.demo.config;

import com.example.demo.model.SocketSessionModel;
import com.example.demo.vo.SocketCommand;
import com.example.demo.vo.SocketMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

public class WebSocketRequestHandler extends BinaryWebSocketHandler
{
  @Autowired
  protected ApplicationEventPublisher dispatcher;

  @Autowired
  private SocketSessionModel socketSessionModel;

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
    if (socketMessageVo.getCommand().equals(SocketCommand.OPENCAFE))
    {
      socketSessionModel.addCafe(socketMessageVo.getCafeId(), session);
    } else if (socketMessageVo.getCommand().equals(SocketCommand.CLOSECAFE))
    {
      socketSessionModel.removeCafe(socketMessageVo.getCafeId());
    }else if(socketMessageVo.getCommand().equals(SocketCommand.MAKEORDER)){
      socketSessionModel.getCafeById(socketMessageVo.getCafeId()).sendMessage(new TextMessage("tableId:"+socketMessageVo.getTableId()));

    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
  {
    socketSessionModel.removeCafe(session);
  }
}
