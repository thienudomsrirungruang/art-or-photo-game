package com.thien.service;

import com.thien.dao.PictureDao;
import com.thien.entity.PictureInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureGetter {

    @Autowired
    private PictureDao pd;

    public PictureInfo getRandomPicture(){
        List<PictureInfo> rs = pd.getRandomPicturePath();
        if( null != rs || !rs.isEmpty() ){
            return rs.get(0);
        }

        return null;
    }
}
