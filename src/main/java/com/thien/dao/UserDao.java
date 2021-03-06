package com.thien.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class UserDao {
    private static final String GET_RANDOM_PICTURE_SQL = "select id from users where username=?;";
    private static final String ENTER_NEW_USER_SQL = "insert into users (username, pwd, enabled) values (?, ?, 1);";
    private static final String GET_USER_ID_SQL = "select id from users where username = ?;";
    private static final String SET_USER_ROLE_SQL = "insert into user_roles (user_id, role) values (?, ?);";
    private static final String GET_TOTAL_PLAY_COUNT_BY_USER_SQL  = "select count(score) from score where user_id=?;";
    private static final String GET_TOTAL_PLAY_COUNT_BY_GAME_SQL  = "select count(score) from score where user_id=? and game_id=?;";
    private static final String GET_USER_HIGH_SCORE_BY_GAME_SQL = "select max(score) from score where user_id = ? and game_id = ?;";
    private static final String GET_USER_RECENT_SCORES_SQL = "select (score) from score where user_id = ? and game_id = ? order by score_date desc, id desc limit ?";
    private static final String CHECK_USER_EXISTS_SQL = "select * from users where username=?;";
    private static final String CHECK_USERNAME_TAKEN_SQL = "select TOP 1 username FROM users WHERE username = ?;";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkUsernameTaken(String username){
        List<Integer> rs = jdbcTemplate.query(CHECK_USERNAME_TAKEN_SQL, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        }, username);
        return rs.size() >= 1;
    }

    public int getIdByUsername(String username) throws Exception{
        List<Integer> rs = jdbcTemplate.query(GET_RANDOM_PICTURE_SQL, new RowMapper<Integer>(){
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        }, username);
        return rs.get(0);
    }

    public void enterNewUser(String username, String encodedPassword) {
        jdbcTemplate.update(ENTER_NEW_USER_SQL, username, encodedPassword);
        try {
            jdbcTemplate.update(SET_USER_ROLE_SQL, getIdByUsername(username), "user");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkUserExists(String username){
        List<Integer> rs = jdbcTemplate.query(CHECK_USER_EXISTS_SQL, new RowMapper<Integer>(){
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        }, username);
        return rs.size() > 0;
    }

    public int getPlayCountByUsername(String username){
        List<Integer> rs = jdbcTemplate.query(GET_TOTAL_PLAY_COUNT_BY_USER_SQL, new RowMapper<Integer>(){
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        }, getUserIdByUsername(username));
        return rs.get(0);
    }

    public int getPlayCountByGame(String username, int gameID) {
        List<Integer> rs = jdbcTemplate.query(GET_TOTAL_PLAY_COUNT_BY_GAME_SQL, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        }, getUserIdByUsername(username), gameID);
        return rs.get(0);
    }

    public int getHighScoreByGame(String username, int gameID){
        List<Integer> rs = jdbcTemplate.query(GET_USER_HIGH_SCORE_BY_GAME_SQL, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        }, getUserIdByUsername(username), gameID);
        if(rs.size() > 0) {
            return rs.get(0);
        }
        return Integer.MIN_VALUE;
    }

    public int[] getUserRecentScores(String username, int gameID, int numberScores){
        List<Integer> rs = jdbcTemplate.query(GET_USER_RECENT_SCORES_SQL, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        }, getUserIdByUsername(username), gameID, numberScores);
        int[] output = new int[rs.size()];
        for (int i = 0; i < rs.size(); i++) {
            output[i] = rs.get(i);
        }
        return output;
    }

    public int getUserIdByUsername(String username){
        List<Integer> rs = jdbcTemplate.query(GET_USER_ID_SQL, new RowMapper<Integer>(){
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        }, username);
        return rs.get(0);
    }


}
