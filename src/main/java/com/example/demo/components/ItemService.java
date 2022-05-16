package com.example.demo.components;

import com.example.demo.entities.Item;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ItemService extends ReloadModel
{
  //key: cafeId, value: products of Cafe
  private Map<Integer, List<Item>> cafeMap;
  private Map<Integer,Map<String, List<Item>>> mapByCategory;

  @Autowired
  private ProductRepository productRepository;

  @PostConstruct
  public void started()
  {
    cafeMap = new ConcurrentHashMap<>();
    mapByCategory = new ConcurrentHashMap<>();
  }

  public List<Item> getAllItems(int businessId){
    return cafeMap.get(businessId);
  }

  public List<Item> getItemsByCategory(int businessId, String category){
    return mapByCategory.get(businessId).get(category);
  }

  @Override
  public void reload()
  {
    List<Item> items = productRepository.findAll();

    for (Item item : items)
    {
      if (!cafeMap.containsKey(item.getBusinessId()))
      {
        cafeMap.put(item.getBusinessId(), new ArrayList<>());
      }
      cafeMap.get(item.getBusinessId()).add(item);

      if(!mapByCategory.containsKey(item.getBusinessId())){
          mapByCategory.put(item.getBusinessId(),new ConcurrentHashMap<>());
      }

      if (!mapByCategory.get(item.getBusinessId()).containsKey(item.getCategory())){
        mapByCategory.get(item.getBusinessId()).put(item.getCategory(),new ArrayList<>());
      }

      mapByCategory.get(item.getBusinessId()).get(item.getCategory()).add(item);
    }
  }
}
