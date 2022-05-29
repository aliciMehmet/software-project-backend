package com.example.demo.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>
{
  public User getUserByUsername(@Param("username") String username);

  public List<User> getByRole(String role);

  public User getUserById(int id);

}

