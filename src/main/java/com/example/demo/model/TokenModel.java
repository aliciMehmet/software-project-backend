package com.example.demo.model;

import com.example.demo.security.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TokenModel
{
  private Map<String, User> tokenUserMap;

  @PostConstruct
  public void started(){
    tokenUserMap = new HashMap<>();
  }

  public String loginUser(User user){
    String token = UUID.randomUUID().toString();
    tokenUserMap.put(token,user);
    return token;
  }
}
