package com.thien.service;

import com.thien.entity.PictureInfo;
import com.thien.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureAdder {

    @Autowired
    private PictureRepository pr;

    public void uploadImage(String path, boolean is_photo){
        PictureInfo imageInfo = new PictureInfo();
        imageInfo.setIsPhoto(is_photo);
        imageInfo.setPath(path);
        pr.save(imageInfo);
    }
}
