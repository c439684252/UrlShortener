package com.cyril.urlshortener.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

// todo
public class LoginController {

    @PostMapping(value = "/user/login")
    @ResponseBody
    public String login(@RequestParam("username") String username ,
                        @RequestParam("password")String password , Map<String,Object> map){
        return username + " / " + password;
    }
}
