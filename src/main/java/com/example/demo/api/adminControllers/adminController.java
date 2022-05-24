package com.example.demo.api.adminControllers;

import com.example.demo.api.DataResult;
import com.example.demo.components.*;
import com.example.demo.entities.Item;
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

  @Autowired
  private BusinessService businessService;

  @Autowired
  private WaiterService waiterService;

  @PostMapping("/addItem")
  public void addItem(@RequestBody Item item)
  {
    itemService.addItem(item);
  }

  @PostMapping("/updateItem")
  public void updateItem(@RequestBody Item item)
  {
    itemService.updateItem(item);
  }

  @PostMapping("/deleteItem")
  public void deleteItem(@RequestBody Item item)
  {
    itemService.deleteItem(item);
  }

  @GetMapping("/getAllProducts")
  public DataResult<Map<String, List<Item>>> getAllProducts(@RequestParam String token)
  {
    User user = authService.tokenUserMap.get(token);

    return new DataResult<>(itemService.mapByCategory.get(user.getBusinessId()));
  }

  @GetMapping("/getTablesStatus")
  public DataResult<Map<Integer,Boolean>> getTablesStatus(@RequestParam String token){
    User user = authService.tokenUserMap.get(token);

    return new DataResult<>(businessService.tableMap.get(user.getBusinessId()));
  }
  @GetMapping("/getOnlineWaiters")
  public DataResult<Map<Integer,Boolean>> getOnlineWaiters(@RequestParam String token)
  {
    User user = authService.tokenUserMap.get(token);

    return new DataResult<>(waiterService.waiterMap.get(user.getBusinessId()));
  }

  @GetMapping("/getEmployee")
  public DataResult<List<User>> getEmployee(@RequestParam String token,@RequestParam String role)
  {
    User user = authService.tokenUserMap.get(token);

    return new DataResult<>(businessService.getEmployee(role));
  }



}
