package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@CrossOrigin
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
//  @Bean
//  public UserDetailsService userDetailsService()
//  {
//    return new UserDetailsServiceImpl();
//  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder()
  {
    return new BCryptPasswordEncoder();
  }

//  @Bean
//  public DaoAuthenticationProvider authenticationProvider()
//  {
//    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//    authProvider.setUserDetailsService(userDetailsService());
//    authProvider.setPasswordEncoder(passwordEncoder());
//
//    return authProvider;
//  }

//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception
//  {
//    auth.authenticationProvider(authenticationProvider());
//  }

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
//    http.csrf().and().authorizeRequests()
//            .antMatchers("/admin/*").permitAll()
//            .antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
//            .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
//            .antMatchers("/admin/login").permitAll()
//            .antMatchers("/mod/**").hasAuthority("MOD")
//            .antMatchers("/admin/**").hasAuthority("ADMIN")
//            .antMatchers("/websocket").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin().permitAll()
//            .and()
//            .logout().permitAll()
//            .and()
//            .exceptionHandling().accessDeniedPage("/403")
//    ;
    http.csrf().disable().cors().and().authorizeRequests().antMatchers("/**").permitAll();
  }
}