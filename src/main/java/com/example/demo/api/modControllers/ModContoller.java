package com.example.demo.api.modControllers;

import com.example.demo.security.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mod")
public class ModContoller
{
  @PostMapping("/addAdmin")
  public void addCustomer(@RequestBody User cafeOwner)
  {
    System.out.println("add customer istegi geldi");
  }

  @GetMapping("/deneme")
  public void deneme(){
    System.out.println("dfkgjfd");
  }
}
