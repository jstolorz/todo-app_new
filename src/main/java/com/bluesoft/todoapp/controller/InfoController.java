package com.bluesoft.todoapp.controller;

import com.bluesoft.todoapp.TaskConfigurationProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class InfoController {

    @Value("${spring.datasource.url}")
    private String url;

    private final TaskConfigurationProperties myProp;

    InfoController(final TaskConfigurationProperties myProp) {
        this.myProp = myProp;
    }

    @GetMapping("/info/url")
    String url(){
       return url;
    }

    @GetMapping("/info/prop")
    boolean myProp(){
      return myProp.isAllowMultipleTasksFromTemplate();
    }
}
