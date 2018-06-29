package com.thien.controller;

import com.thien.service.PictureAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class PictureUploadController {

    @Value("${image.path}")
    private String imagePath;

    @Autowired
    private PictureAdder pa;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<?> uploadImage(@RequestParam("category") String category, @RequestParam("image") MultipartFile image){
        if (image.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {
            byte[] bytes = image.getBytes();
            Path path = Paths.get(imagePath + image.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        pa.uploadImage(image.getOriginalFilename(), category.equals("photo"));

        return new ResponseEntity("Successfully uploaded - " +
                image.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/upload")
    public String getUploadPage(){
        return "upload";
    }

}
