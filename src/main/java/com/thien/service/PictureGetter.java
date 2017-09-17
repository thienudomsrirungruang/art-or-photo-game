package com.thien.service;

import com.thien.dao.PictureDao;
import com.thien.entity.PictureInfo;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class PictureGetter {
    private PictureDao pd;

    public PictureGetter(){
        pd = PictureDao.getInstance();
    }

    public PictureInfo getRandomPicture(){
        ResultSet rs = pd.getRandomPicturePath();
        try {
            if(rs.next()){
                PictureInfo output = new PictureInfo();
                output.setPath(rs.getString(1));
                output.setIsPhoto(rs.getBoolean(2));
                return output;
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
