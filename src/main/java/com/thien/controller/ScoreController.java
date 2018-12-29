package com.thien.controller;

import com.thien.entity.HighScoreInfo;
import com.thien.entity.ScoreInfo;
import com.thien.service.ScoreService;
import com.thien.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private UserService uig;

    @PostMapping("/enter-score/{game_id}/{score}")
    @ResponseBody
    public void enterScore(@PathVariable("game_id") String gameID, @PathVariable("score") String score, Principal principal){
        try {
            int userId = uig.getIdByUsername(principal.getName());
            scoreService.enterScore(Integer.parseInt(gameID), userId, Integer.parseInt(score));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping("/get-highscore/global/{game_id}")
    @ResponseBody
    public HighScoreInfo getGlobalHighScore(@PathVariable("game_id") String gameID){
        try{
            return scoreService.getGlobalHighScore(Integer.parseInt(gameID));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/get-highscore/global/{game_id}/weekly")
    @ResponseBody
    public ScoreInfo getGlobalWeeklyHighScore(@PathVariable("game_id") String gameId){
        try{
             return scoreService.getWeeklyTopScore(Integer.parseInt(gameId));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
