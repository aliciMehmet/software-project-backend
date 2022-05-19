package com.example.demo.api;

import com.example.demo.api.vo.MakeOrderRequest;
import com.example.demo.components.KitchenService;
import com.example.demo.entities.Item;
import com.example.demo.components.ItemService;
import com.example.demo.repositories.ItemRepository;
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
    kitchenService.sendNewOrderNotification(request.getBusinessId(),request.getItemName(),request.getCount());
  }

}