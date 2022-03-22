package com.example.demo.api.adminControllers;

import com.example.demo.api.vo.LoginRequestVo;
import com.example.demo.entities.Product;
import com.example.demo.model.TokenModel;
import com.example.demo.security.User;
import com.example.demo.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class adminController
{
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  private TokenModel tokenModel;

  @PostMapping("/login")
  public String login(@RequestBody LoginRequestVo requestVo) throws Exception
  {
    String username = requestVo.getUsername();
    User user = userRepository.getUserByUsername(username);

    if (user == null)
    {
    }

    if (passwordEncoder.matches(requestVo.getPassword(), user.getPassword()))
    {
      String token = tokenModel.loginUser(user);
      return token;
    } else
    {
      throw new Exception("Password incorrect");
    }
  }

  @PostMapping("/addProduct")
  public void addProduct(HttpSession session, @RequestBody Product product)
  {
    System.out.println("fkgjfg");
  }

  @PostMapping("/updateProduct")
  public void updateProduct(@RequestBody Product product)
  {

  }
}
