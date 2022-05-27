package com.example.demo.api.adminControllers;

import com.example.demo.api.DataResult;
import com.example.demo.components.*;
import com.example.demo.entities.Item;
import com.example.demo.entities.Order;
import com.example.demo.security.User;
import com.example.demo.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

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
  private OrderService orderService;

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

  @GetMapping("/getAllOrders")
  public DataResult<Map<Integer, List<Order>>> getAllOrders(@RequestParam String token)
  {
    User user = authService.tokenUserMap.get(token);

    return new DataResult<>(orderService.orderMap.get(user.getBusinessId()));
  }

  @GetMapping("/getWaitingOrders")
  public DataResult< List<Order>> getWaitingOrders(@RequestParam String token){
    User user = authService.tokenUserMap.get(token);
    Map<Integer, List<Order>> allOrders = orderService.orderMap.get(user.getBusinessId());

    List<Order> waitingOrders = new ArrayList<>();

    for (Map.Entry<Integer, List<Order>> entry : allOrders.entrySet()) {
      waitingOrders.addAll(entry.getValue().stream().filter(order -> !order.isServed()).collect(Collectors.toCollection(ArrayList::new)));
    }

    return new DataResult<>(waitingOrders);
  }

}
