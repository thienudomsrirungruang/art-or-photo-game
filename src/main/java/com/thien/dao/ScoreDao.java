package com.thien.dao;

import com.thien.entity.ScoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class ScoreDao {

    private static final String INSERT_SCORE_SQL = "insert into score (game_id, user_id, score, score_date) values (?, ?, ?, ?)";
    private static final String GET_GLOBAL_HIGH_SCORE_SQL = "select score from art_or_photo.score where game_id = ? order by score desc limit 1";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertScore(int gameID, int userID, int score, Date date){
        jdbcTemplate.update(INSERT_SCORE_SQL, gameID, userID, score, date);
    }

    public ScoreInfo getGlobalHighScore(int gameID){
        List<Integer> results = jdbcTemplate.query(GET_GLOBAL_HIGH_SCORE_SQL, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        }, gameID);
        ScoreInfo si = new ScoreInfo();
        if(results.isEmpty()){
            si.setScore(0);
            si.setHasScore(false);
        }else{
            si.setScore(results.get(0));
            si.setHasScore(true);
        }
        return si;
    }
}
