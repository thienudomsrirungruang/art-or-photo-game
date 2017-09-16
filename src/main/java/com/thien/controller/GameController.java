package com.thien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameController {
    @RequestMapping("")
    public String getMainPage(){
        return "main";
    }
}
