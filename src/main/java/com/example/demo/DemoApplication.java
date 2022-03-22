package com.example.demo;

import com.example.demo.security.Role;
import com.example.demo.security.User;
import com.example.demo.security.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication
{
  public static void main(String[] args)
  {

    ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);

    BCryptPasswordEncoder bCryptPasswordEncoder = run.getBean(BCryptPasswordEncoder.class);
    UserRepository userRepository = run.getBean(UserRepository.class);

//    User user = new User();
//    user.setUsername("mod");
//    user.setPassword(bCryptPasswordEncoder.encode("pass"));
//    user.setEnabled(true);
//    user.setRole(Role.MOD);
//    userRepository.save(user);
  }
}
