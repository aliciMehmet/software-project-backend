//package com.example.demo.security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//
//public class MyUserDetails implements UserDetails {
//
//  private User user;
//
//  public MyUserDetails(User user) {
//    this.user = user;
//  }
//
//  public Long getUserId(){
//    return user.getId();
//  }
//
//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//    String role = user.getRole();
//    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//
//      authorities.add(new SimpleGrantedAuthority(role));
//
//
//    return authorities;
//  }
//
//  @Override
//  public String getPassword() {
//    return user.getPassword();
//  }
//
//  @Override
//  public String getUsername() {
//    return user.getUsername();
//  }
//
//  @Override
//  public boolean isAccountNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isAccountNonLocked() {
//    return true;
//  }
//
//  @Override
//  public boolean isCredentialsNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isEnabled() {
//    return user.isEnabled();
//  }
//
//}