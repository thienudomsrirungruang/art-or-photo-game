package com.thien.controller;

import com.thien.entity.PictureInfo;
import com.thien.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class GameController {

    @Value("${image.path}")
    private String imagePath;

    @Autowired
    private PictureService pictureService;

    @GetMapping("")
    public String getMainPage(){
        return "main";
    }

    @PostMapping("/random-picture")
    @ResponseBody
    public PictureInfo getRandomPicture(){
        return pictureService.getRandomPicture();
    }

    @GetMapping("/image/{imageName}")
    @ResponseBody
    public byte[] getPictureAsBytes(@PathVariable("imageName") String imageName) throws IOException {
        String stringPath = imagePath + imageName + ".png";
        Path path = Paths.get( stringPath );
        byte[] bytesRead = Files.readAllBytes(path);
        return bytesRead;
    }
}
