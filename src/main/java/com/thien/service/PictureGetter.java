package com.thien.service;

import com.thien.entity.PictureInfo;
import com.thien.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PictureGetter {

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
}
