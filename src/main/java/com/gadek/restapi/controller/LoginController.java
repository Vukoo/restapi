package com.gadek.restapi.controller;

import com.gadek.restapi.config.LoginCredentials;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials loginCredentials){
    }

    @GetMapping("/secured")
    public String login(){
        return "secured";
    }
}
