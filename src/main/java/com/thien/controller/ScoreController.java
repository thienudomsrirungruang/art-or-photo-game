package com.thien.controller;

import com.thien.entity.ScoreInfo;
import com.thien.service.ScoreAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScoreController {
    @Autowired
    private ScoreAdder scoreAdder;

    @RequestMapping("/enter-score/{game_id}/{user_id}/{score}")
    @ResponseBody
    public void enterScore(@PathVariable("game_id") String gameID, @PathVariable("user_id") String userID, @PathVariable("score") String score){
        try {
            scoreAdder.enterScore(Integer.parseInt(gameID), Integer.parseInt(userID), Integer.parseInt(score));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/get-highscore/global/{game_id}")
    @ResponseBody
    public ScoreInfo getGlobalHighScore(@PathVariable("game_id") String gameID){
        try{
            return scoreAdder.getGlobalHighScore(Integer.parseInt(gameID));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
