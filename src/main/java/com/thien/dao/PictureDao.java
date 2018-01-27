package com.thien.dao;

import com.thien.entity.PictureInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
@Transactional
public class PictureDao {
    private static final String GET_RANDOM_PICTURE_SQL = "select * from pics order by rand() limit 1;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PictureInfo> getRandomPicturePath(){
        List<PictureInfo> rs = jdbcTemplate.query( GET_RANDOM_PICTURE_SQL, new RowMapper<PictureInfo>(){

            @Override
            public PictureInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                PictureInfo info = new PictureInfo();
                info.setPath(rs.getString("path"));
                info.setIsPhoto(rs.getInt("is_photo") == 1);
                return info;
            }
        });
        return rs;
    }
}
