package com.example.demo.components;

import com.example.demo.api.vo.LoginResponseVo;
import com.example.demo.security.User;
import com.example.demo.security.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private TokenModel tokenModel;
    public String login(String username,String password) throws Exception {
        User user = userRepository.getUserByUsername(username);

        if (user == null)
        {
            throw new Exception("Username not found!");
        }

        if (passwordEncoder.matches(password, user.getPassword()))
        {
            String token = tokenModel.loginUser(user);

            ObjectMapper objectMapper = new ObjectMapper();

            String json = objectMapper.writeValueAsString(new LoginResponseVo(token,user.getRole()));
            return json;
        } else
        {
            throw new Exception("Password is incorrect!");
        }
    }

    public void logout(String token){

    }
}
