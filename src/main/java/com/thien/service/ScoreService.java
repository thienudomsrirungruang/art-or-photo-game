package com.thien.service;

import com.thien.entity.HighScoreInfo;
import com.thien.entity.ScoreInfo;
import com.thien.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository sr;


    @Transactional
    public void enterScore(int gameID, int userID, int score) {
//        sd.insertScore(gameID, userID, score, new Date());
        ScoreInfo input = new ScoreInfo();
        input.setGameId(gameID);
        input.setUserId(userID);
        input.setScore(score);
        input.setScoreDate(new Date());
        sr.save(input);
    }

    public HighScoreInfo getGlobalHighScore(int gameID){
//        return sd.getGlobalHighScore(gameID);
        List<ScoreInfo> results = sr.findByGameIdOrderByScoreDesc(gameID);
        HighScoreInfo output = new HighScoreInfo();
        if(results.isEmpty()){
            output.setHasScore(false);
        }else{
            output.setHasScore(true);
            output.setScore(results.get(0).getScore());
        }
        return output;
    }

    public ScoreInfo getWeeklyTopScore(int gameId){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        List<ScoreInfo> result =  sr.findWeeklyTopScoreFromDate(c.getTime(), gameId);
        return result.size() == 0 ? null : result.get(0);
    }
}
