package com.gadek.restapi.controller;

import com.gadek.restapi.config.LoginCredentials;
import com.gadek.restapi.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
