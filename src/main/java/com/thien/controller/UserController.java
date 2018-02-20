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

    @Autowired
    private UserInfoGetter uig;

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

    @RequestMapping("/dashboard/{username}")
    public String getUserDashboard(){
        return "userdashboard";
    }



    @RequestMapping("/dashboard/")
    public String getDashboardRedirect(){
        return "dashboardredirect";
    }

    @PostMapping("/user/{username}")
    @ResponseBody
    public boolean userExists(@PathVariable String username){
        return uig.userExists(username);
    }

    @PostMapping("/user/{username}/playcount")
    @ResponseBody
    public int getPlayCount(@PathVariable String username){
        return uig.getPlayCount(username);
    }

    @RequestMapping(value = "/user/getusername", method = RequestMethod.POST)
    @ResponseBody
    public String currentUsername(Principal principal) {
        return principal.getName();
    }

}
