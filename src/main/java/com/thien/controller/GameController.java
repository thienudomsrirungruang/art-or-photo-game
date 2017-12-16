package com.thien.controller;

import com.thien.entity.PictureInfo;
import com.thien.service.PictureGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class GameController {

    private Logger log = LoggerFactory.getLogger(GameController.class);

    @Value("${image.path}")
    private String imagePath;

    @Autowired
    private PictureGetter pictureGetter;

    @RequestMapping("")
    public String getMainPage(){
        return "main";
    }

    @RequestMapping("/random-picture")
    @ResponseBody
    public PictureInfo getRandomPicture(){
        return pictureGetter.getRandomPicture();
    }

    @RequestMapping("/image/{imageName}")
    @ResponseBody
    public byte[] getPictureAsBytes(@PathVariable("imageName") String imageName) throws IOException {
        String stringPath = imagePath + imageName + ".png";
        log.info( stringPath );
        Path path = Paths.get( stringPath );
        byte[] bytesRead = Files.readAllBytes(path);
        return bytesRead;
    }

}
