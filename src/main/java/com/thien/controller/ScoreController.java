package com.thien.controller;

import com.thien.dao.UserDao;
import com.thien.entity.ScoreInfo;
import com.thien.service.ScoreAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class ScoreController {
    @Autowired
    private ScoreAdder scoreAdder;

    @Autowired
    private UserDao userDao;

    @PostMapping("/enter-score/{game_id}/{score}")
    @ResponseBody
    public void enterScore(@PathVariable("game_id") String gameID, @PathVariable("score") String score, Principal principal){
        try {
            int userId = userDao.getIdByUsername(principal.getName());
            scoreAdder.enterScore(Integer.parseInt(gameID), userId, Integer.parseInt(score));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping("/get-highscore/global/{game_id}")
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
