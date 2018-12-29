package com.thien.service;

import com.thien.entity.PictureInfo;
import com.thien.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pr;

    private Random random = new Random();

    public PictureInfo getRandomPicture(){

        List<PictureInfo> result = (List<PictureInfo>) pr.findAll();
        if( null != result && !result.isEmpty() ){
            return result.get(random.nextInt(result.size()));
        }

        return null;
    }

    @Transactional
    public void uploadImage(String path, boolean is_photo){
        PictureInfo imageInfo = new PictureInfo();
        imageInfo.setIsPhoto(is_photo);
        imageInfo.setPath(path);
        pr.save(imageInfo);
    }

    @Transactional
    public void updatePath(PictureInfo upPictureInfo ) {
        PictureInfo imageInfo = pr.findOne(1L);
        imageInfo.setPath(upPictureInfo.getPath());
    }

}
