package com.example.demo.api.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequestVo
{
  private String username;
  private String password;
  private String role;
  private String token;
}
