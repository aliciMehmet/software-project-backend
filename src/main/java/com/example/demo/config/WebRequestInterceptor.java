package com.example.demo.config;

import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebRequestInterceptor implements AsyncHandlerInterceptor
{
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
    return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
  }
}
