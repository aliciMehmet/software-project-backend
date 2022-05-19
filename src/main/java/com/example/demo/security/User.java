package com.example.demo.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User
{
  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String username;

  private String password;

  private int businessId;

  private boolean enabled;

  private String role;

//  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//  @JoinTable(
//          name = "users_roles",
//          joinColumns = @JoinColumn(name = "user_id"),
//          inverseJoinColumns = @JoinColumn(name = "role_id")
//  )

}