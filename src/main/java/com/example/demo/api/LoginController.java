package com.example.demo.api;

import com.example.demo.api.vo.LoginRequestVo;
import com.example.demo.components.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestVo requestVo) throws Exception
    {
      return authService.login(requestVo.getUsername(), requestVo.getPassword());
    }

    @PostMapping("/logout")
    public void logout(@RequestParam String token)
    {
        authService.logout(token);
    }
}
