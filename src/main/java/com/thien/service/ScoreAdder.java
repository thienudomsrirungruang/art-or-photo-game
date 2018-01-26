package com.thien.service;

import com.thien.dao.ScoreDao;
import com.thien.entity.ScoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreAdder {

    @Autowired
    private ScoreDao sd;

    public void enterScore(int gameID, int userID, int score) {
        sd.insertScore(gameID, userID, score);
    }

    public ScoreInfo getGlobalHighScore(int gameID){
        return sd.getGlobalHighScore(gameID);
    }
}
