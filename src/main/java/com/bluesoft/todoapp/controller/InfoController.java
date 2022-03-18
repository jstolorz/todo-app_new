package com.bluesoft.todoapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
class InfoController {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${task.allowMultipleTasksFromTemplate}")
    private String myProp;

    @GetMapping("/info/url")
    String url(){
       return url;
    }

    @GetMapping("/info/prop")
    String myProp(){
      return myProp;
    }
}
