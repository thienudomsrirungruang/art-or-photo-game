package com.thien.controller;

import com.thien.entity.UserSubmitInfo;
import com.thien.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserService uig;


    @GetMapping("/login")
    public String getLoginScreen(){
        return "login";
    }

    @GetMapping("/login/signup")
    public String getSignupScreen(){
        return "signup";
    }

    @PostMapping("/login/signup")
    public String greetingSubmit(@ModelAttribute UserSubmitInfo usi) {
        if(userService.checkUserExists(usi.getUsername())){
            return "loginusernametakenredirect";
        }else {
            userService.enterUser(usi.getUsername(), usi.getPassword());
            return "loginaccountcreatedredirect";
        }
    }

    @PostMapping("/login/checklogin")
    @ResponseBody
    public boolean isLogin(Principal principal){
        return principal != null;
    }

    @PostMapping("/user/id/{id}")
    @ResponseBody
    public String getUsernameById(@PathVariable int id){
        return uig.getUsernameById(id);
    }
}
