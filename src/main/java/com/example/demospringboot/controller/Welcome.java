package com.example.demospringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Welcome {

    @GetMapping("/welcome")
    public String sayHi()
    {
      return "Hello Sowmya";
    }

    @GetMapping("/")
    public String home()
    {
        return "This is the welcome page";
    }
}
