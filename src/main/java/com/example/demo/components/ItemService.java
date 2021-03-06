package com.example.demo.components;

import com.example.demo.entities.Item;
import com.example.demo.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
//TODO: addItem ve updateItem metodları yazılmalı
public class ItemService extends ReloadModel
{
  //key: cafeId, value: products of Cafe
  public Map<Integer, List<Item>> cafeMap;
  public Map<Integer,Map<String, List<Item>>> mapByCategory;

  @Autowired
  private ItemRepository itemRepository;

  @PostConstruct
  public void started()
  {
    cafeMap = new ConcurrentHashMap<>();
    mapByCategory = new ConcurrentHashMap<>();
  }

  public List<Item> getAllItems(int businessId){
    if(!cafeMap.containsKey(businessId)){
      return new ArrayList<>();
    }
    return cafeMap.get(businessId);
  }

  public List<Item> getItemsByCategory(int businessId, String category){
    return mapByCategory.get(businessId).get(category);
  }

  public void addItem(Item item){
    itemRepository.save(item);

    cafeMap.get(item.getBusinessId()).add(item);
    mapByCategory.get(item.getBusinessId()).get(item.getCategory()).add(item);
  }

  public void updateItem(Item item){
    itemRepository.save(item);

    for (Item item1 : cafeMap.get(item.getBusinessId())) {
      if(item1.getId() == item.getId()){
        cafeMap.get(item.getBusinessId()).remove(item1);
        cafeMap.get(item.getBusinessId()).add(item);
        mapByCategory.get(item.getBusinessId()).get(item.getCategory()).remove(item1);
        mapByCategory.get(item.getBusinessId()).get(item.getCategory()).add(item);

        break;
      }
    }
  }

  public void deleteItem(Item item){
    itemRepository.delete(item);
    for (Item item1 : cafeMap.get(item.getBusinessId())) {
      if(item1.getId() == item.getId()){
        cafeMap.get(item.getBusinessId()).remove(item1);
        mapByCategory.get(item.getBusinessId()).get(item.getCategory()).remove(item1);

        break;
      }
    }
  }

  @Override
  public void reload()
  {
    List<Item> items = itemRepository.findAll();

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
