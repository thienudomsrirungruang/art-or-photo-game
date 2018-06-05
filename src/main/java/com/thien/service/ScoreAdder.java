package com.thien.service;

import com.thien.dao.ScoreDao;
import com.thien.entity.ScoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScoreAdder {

    @Autowired
    private ScoreDao sd;


    public void enterScore(int gameID, int userID, int score) {
        sd.insertScore(gameID, userID, score, new Date());
    }

    public ScoreInfo getGlobalHighScore(int gameID){
        return sd.getGlobalHighScore(gameID);
    }
}
