package com.thien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String getLoginScreen(){
        return "login";
    }

    @RequestMapping("/login/signup")
    public String getSignupScreen(){
        return "signup";
    }

}
