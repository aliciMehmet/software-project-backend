package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer
{
  @Override
  public void addInterceptors(InterceptorRegistry registry)
  {
    registry.addInterceptor(webRequestInterceptor());
  }

  @Bean
  public WebRequestInterceptor webRequestInterceptor()
  {
    return new WebRequestInterceptor();
  }
}