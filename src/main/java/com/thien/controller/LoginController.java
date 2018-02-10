package com.thien.controller;

import com.thien.entity.UserSubmitInfo;
import com.thien.service.UserAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

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
