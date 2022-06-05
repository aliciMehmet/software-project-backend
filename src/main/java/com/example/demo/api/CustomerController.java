package com.example.demo.api;

import com.example.demo.api.vo.MakeOrderRequest;
import com.example.demo.components.*;
import com.example.demo.entities.Item;
import com.example.demo.entities.Order;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private ItemService itemService;

  @Autowired
  private KitchenService kitchenService;

  @Autowired
  private OrderService orderService;

  @Autowired
  private WaiterService waiterService;

  @Autowired
  private TableService tableService;

  @GetMapping("/getAllProducts")
  public DataResult<Map<String,List<Item>>> getAllProducts(@RequestParam int cafeId)
  {
    List<Item> allItems = itemService.getAllItems(cafeId);

    Map<String,List<Item>> map = new HashMap<>();

    for (Item item : allItems)
    {
      if(!map.containsKey(item.getCategory())){
        map.put(item.getCategory(),new ArrayList<>());
      }

      map.get(item.getCategory()).add(item);
    }

    //TODO itemservice'ten mapByCategory bundan al döndür
    return new DataResult<>(map);
  }

  @GetMapping("/getProductsByCategory")
  public List<Item> getAllProductsByCategory(@RequestParam int cafeId, @RequestParam String category)
  {
    return itemRepository.getByBusinessIdAndCategory(cafeId, category);
  }

  @PostMapping("/makeOrder")
  public void makeOrder(@RequestBody MakeOrderRequest request) throws IOException {
    
    List<Item> items = itemService.getAllItems(request.getBusinessId());
    String orderedItemName = null;

    for (Item item : items) {
      if(item.getId() == request.getItemId()){
        orderedItemName = item.getName();
        break;
      }
    }

    orderService.placeOrder(request.getItemId(),request.getBusinessId(),request.getTableId(),request.getCount());
    kitchenService.sendNewOrderNotification(request.getBusinessId(),orderedItemName,request.getCount(),request.getTableId());
  }

  @GetMapping("/getReceipt")
  public DataResult<List<Order>> getReceipt(@RequestParam int businessId, @RequestParam int tableId)
  {

    return new DataResult<>(orderService.getReceipt(businessId,tableId));
  }
  @GetMapping("/callWaiter")
  public void callWaiter(@RequestParam int businessId, @RequestParam int tableId) throws IOException {
    tableService.callWaiter(businessId,tableId);
  }

}