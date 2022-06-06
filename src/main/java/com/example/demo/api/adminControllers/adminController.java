package com.example.demo.api.adminControllers;

import com.example.demo.api.DataResult;
import com.example.demo.api.vo.AddUserRequestVo;
import com.example.demo.api.vo.TableStatus;
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
  public DataResult<List<Order>> getAllOrders(@RequestParam String token)
  {
    User user = authService.tokenUserMap.get(token);

    return new DataResult<>(orderService.getAllByBusinessId(user.getBusinessId()));
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

  @PostMapping("/addUser")
  public void addUser(@RequestBody AddUserRequestVo request)
  {
    User user = authService.tokenUserMap.get(request.getToken());
    authService.addUser(request,user.getBusinessId());
  }


  @PostMapping("/deleteUser")
  public void deleteUser(@RequestBody User user)
  {
    authService.deleteUser(user);
  }

  @GetMapping("/getUserById")
  public DataResult<User> getUserById(@RequestParam int id)
  {

    return new DataResult<>(authService.getUserById(id));
  }

  @GetMapping("/getTablesStatus")
  public DataResult<List<TableStatus>> getTablesStatus(@RequestParam String token){
    User user = authService.tokenUserMap.get(token);

    List<TableStatus> tableStatuses = new ArrayList<>();

    Map<Integer, Boolean> tables = businessService.tableMap.get(user.getBusinessId());
    for (Map.Entry<Integer, Boolean> entry : tables.entrySet()) {
      tableStatuses.add(new TableStatus(entry.getKey(), entry.getValue()));
    }
    return new DataResult<>(tableStatuses);
  }

  @GetMapping("/getReceipt")
  public DataResult<List<Order>> getReceipt(@RequestParam String token,@RequestParam int tableId){
    User user = authService.tokenUserMap.get(token);

    return new DataResult<>(orderService.getReceipt(user.getBusinessId(),tableId));
  }
  @GetMapping("/completePayment")
  public void completePayment(@RequestParam String token,@RequestParam int tableId){
    User user = authService.tokenUserMap.get(token);

    orderService.completePayment(user.getBusinessId(),tableId);
    businessService.resetTable(user.getBusinessId(),tableId);
  }
}
