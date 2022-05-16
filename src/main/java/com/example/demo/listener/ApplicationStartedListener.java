package com.example.demo.listener;

import com.example.demo.components.ReloadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApplicationStartedListener implements ApplicationListener<ApplicationReadyEvent>
{
  @Autowired
  private ApplicationContext context;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event)
  {
    Map<String, ReloadModel> reloadableModels = context.getBeansOfType(ReloadModel.class);

    for (ReloadModel reloadModel : reloadableModels.values())
    {
      reloadModel.reload();
    }
  }
}
