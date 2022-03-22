package com.example.demo.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long>
{
  public User getUserByUsername(@Param("username") String username);
}