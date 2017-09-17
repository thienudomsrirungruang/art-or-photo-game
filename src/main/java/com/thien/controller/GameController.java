package com.thien.controller;

import com.thien.entity.PictureInfo;
import com.thien.service.PictureGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameController {

    @Autowired
    private PictureGetter pictureGetter;

    @RequestMapping("")
    public String getMainPage(){
        return "main";
    }

    @RequestMapping(value = "/random-picture", method = RequestMethod.POST)
    @ResponseBody
    public PictureInfo getRandomPicture(){
        return pictureGetter.getRandomPicture();
    }

}
