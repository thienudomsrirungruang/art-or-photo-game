package com.thien.service;

import com.thien.entity.HighScoreInfo;
import com.thien.entity.ScoreInfo;
import com.thien.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScoreAdder {

    @Autowired
    private ScoreRepository sr;


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
}
