package com.thien.controller;

import com.thien.entity.UserSubmitInfo;
import com.thien.service.UserAdder;
import com.thien.service.UserInfoGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserAdder userAdder;



    @RequestMapping("/login")
    public String getLoginScreen(){
        return "login";
    }

    @GetMapping("/login/signup")
    public String getSignupScreen(){
        return "signup";
    }

    @PostMapping("/login/signup")
    public String greetingSubmit(@ModelAttribute UserSubmitInfo usi) {
        userAdder.enterUser(usi.getUsername(), usi.getPassword());
        return "login";
    }

}
