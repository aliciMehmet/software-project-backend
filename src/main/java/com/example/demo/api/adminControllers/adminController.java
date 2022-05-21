package com.example.demo.api.adminControllers;

import com.example.demo.api.DataResult;
import com.example.demo.components.AuthService;
import com.example.demo.components.ItemService;
import com.example.demo.entities.Item;
import com.example.demo.components.TokenModel;
import com.example.demo.security.User;
import com.example.demo.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class adminController
{
  @Autowired
  private ItemService itemService;

  @Autowired
  private AuthService authService;
  @PostMapping("/addProduct")
  public void addProduct(HttpSession session, @RequestBody Item item)
  {
    System.out.println("fkgjfg");
  }

  @PostMapping("/updateItem")
  public void updateItem(@RequestBody Item item)
  {

  }

  @GetMapping("/getAllProducts")
  public DataResult<Map<String, List<Item>>> getAllProducts(@RequestParam String token)
  {
    User user = authService.tokenUserMap.get(token);

    return new DataResult<>(itemService.mapByCategory.get(user.getBusinessId()));
  }

}
