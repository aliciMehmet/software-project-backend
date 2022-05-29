package com.example.demo;

import com.example.demo.entities.Item;
import com.example.demo.entities.Table;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.repositories.TableRepository;
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

    ItemRepository itemRepository = run.getBean(ItemRepository.class);

    TableRepository tableRepository = run.getBean(TableRepository.class);

  /* User user = new User();
   user.setUsername("admin");
   user.setPassword(bCryptPasswordEncoder.encode("pass"));
   user.setEnabled(true);
   user.setRole(Role.ADMIN);
   userRepository.save(user);



       user = new User();
      user.setUsername("kitchen");
      user.setPassword(bCryptPasswordEncoder.encode("pass"));
      user.setEnabled(true);
      user.setRole(Role.KITCHEN);
      userRepository.save(user);

    Item item = new Item();
    item.setBusinessId(1);
    item.setName("Hamburger");
    item.setCategory("Food");
    item.setPrice(40);
    item.setStock(100);
    item.setScore(5);

    itemRepository.save(item);


    item = new Item();
    item.setBusinessId(1);
    item.setName("Hookah");
    item.setCategory("Tobacco");
    item.setPrice(12);
    item.setStock(25);
    item.setScore(5);

    itemRepository.save(item);

    item = new Item();
    item.setBusinessId(1);
    item.setName("cips");
    item.setCategory("packet");
    item.setPrice(14);
    item.setStock(40);
    item.setScore(5);

    itemRepository.save(item);

*/

    /*Table table = new Table();
    table.setBusinessId(1);
    table.setTableId(1);

    tableRepository.save(table);

     table = new Table();
    table.setBusinessId(1);
    table.setTableId(2);

    tableRepository.save(table);

    table = new Table();
    table.setBusinessId(1);
    table.setTableId(3);

    tableRepository.save(table);

    table = new Table();
    table.setBusinessId(1);
    table.setTableId(4);

    tableRepository.save(table);*/





  }
}
