package com.gadek.restapi.controller;

import com.gadek.restapi.config.LoginCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @PostMapping("/login")
//    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody LoginCredentials loginCredentials){
    }

    @GetMapping("/secured")
//    @ResponseStatus(HttpStatus.OK)
    public String login(){
        return "secured";
    }
}
