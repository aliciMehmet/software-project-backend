package com.example.demo.components;

import com.example.demo.api.vo.AddUserRequestVo;
import com.example.demo.api.vo.LoginResponseVo;
import com.example.demo.entities.Item;
import com.example.demo.security.Role;
import com.example.demo.security.User;
import com.example.demo.security.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private WaiterService waiterService;

    public Map<String, User> tokenUserMap = new HashMap<>();

    public String login(String username,String password) throws Exception {
        User user = userRepository.getUserByUsername(username);

        if (user == null)
        {
            throw new Exception("Username not found!");
        }

        if (passwordEncoder.matches(password, user.getPassword()))
        {
            String token =  UUID.randomUUID().toString();

            tokenUserMap.put(token,user);

            if(user.getRole().equals(Role.WAITER)){
                waiterService.loginWaiter(user.getBusinessId(),user.getId());
            }

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

    public void addUser(AddUserRequestVo request,int businessId){
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setBusinessId(businessId);
        newUser.setRole(request.getRole());
        userRepository.save(newUser);
    }
    public void deleteUser(User  user){
        userRepository.delete(user);
    }

    public User getUserById(int id){

        return userRepository.getUserById(id);
    }

}


