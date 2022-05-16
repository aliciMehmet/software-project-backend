package com.example.demo.api.adminControllers;

import com.example.demo.entities.Item;
import com.example.demo.components.TokenModel;
import com.example.demo.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class adminController
{
  @PostMapping("/addProduct")
  public void addProduct(HttpSession session, @RequestBody Item item)
  {
    System.out.println("fkgjfg");
  }

  @PostMapping("/updateItem")
  public void updateItem(@RequestBody Item item)
  {

  }

}
