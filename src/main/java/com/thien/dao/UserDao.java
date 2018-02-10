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
public class UserDao {
    private static final String GET_RANDOM_PICTURE_SQL = "select id from users where username=?";
    private static final String ENTER_NEW_USER_SQL = "insert into users (username, pwd, enabled) values (?, ?, 1)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getIdByUsername(String username) throws Exception{
        List<Integer> rs = jdbcTemplate.query(GET_RANDOM_PICTURE_SQL, new RowMapper<Integer>(){
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        }, username);
        return rs.get(0);
    }

    public void enterNewUser(String username, String encodedPassword){
        jdbcTemplate.update(ENTER_NEW_USER_SQL, username, encodedPassword);
    }
}
