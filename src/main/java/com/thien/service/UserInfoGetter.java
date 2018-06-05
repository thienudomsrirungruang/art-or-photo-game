package com.thien.service;

import com.thien.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoGetter {

    @Autowired
    private UserDao ud;

    public boolean userExists(String username){
        return ud.checkUserExists(username);
    }

    public int getPlayCount(String username){
        return ud.getPlayCountByUsername(username);
    }

    public int getPlayCountByGame(String username, int gameID) {
        return ud.getPlayCountByGame(username, gameID);
    }

    public int getHighScoreByGame(String username, int gameID){
        return ud.getHighScoreByGame(username, gameID);
    }

    public int[] getRecentScoresByGame(String username, int gameID, int numberScores){
        return ud.getUserRecentScores(username, gameID, numberScores);
    }
}
