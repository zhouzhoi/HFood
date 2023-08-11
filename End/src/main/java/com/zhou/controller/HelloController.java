package com.zhou.controller;

import com.zhou.entity.User;
import com.zhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    @GetMapping
    public String hello(){
        return "hello";
    }

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list(){
        return userService.listAll();
    }
}

