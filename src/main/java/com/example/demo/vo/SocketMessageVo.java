package com.example.demo.vo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.msgpack.annotation.Message;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
@Message
@Getter
@Setter
public class SocketMessageVo
{
  private static final ObjectMapper mapper = new ObjectMapper();

  private String command;

  private String token;

  private int cafeId;

  private int tableId;


  public static SocketMessageVo parse(TextMessage message) throws IOException
  {
    byte[] bytes = message.asBytes();

    return mapper.readValue(bytes, SocketMessageVo.class);
  }
}
