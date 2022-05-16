package com.example.demo.api.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponseVo {
    String token;
    String role;

}
