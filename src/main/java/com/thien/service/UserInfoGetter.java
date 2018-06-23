package com.thien.service;

import com.thien.dao.UserDao;
import com.thien.entity.ScoreInfo;
import com.thien.entity.UserInfo;
import com.thien.repository.ScoreRepository;
import com.thien.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoGetter {

    @Autowired
    private UserDao ud;

    @Autowired
    private UserRepository ur;

    @Autowired
    private ScoreRepository sr;

    public boolean userExists(String username){
//        return ud.checkUserExists(username);
        return ur.findByUsername(username).size() > 0;
    }

    public int getPlayCount(String username){
//        return ud.getPlayCountByUsername(username);
        return sr.findByUserId(getIdByUsername(username)).size();
    }

    public int getPlayCountByGame(String username, int gameID) {
//        return ud.getPlayCountByGame(username, gameID);
        return sr.findByUserIdAndGameId(getIdByUsername(username), gameID).size();
    }

    public int getHighScoreByGame(String username, int gameID){
//        return ud.getHighScoreByGame(username, gameID);
        List<ScoreInfo> result = sr.findByUserIdAndGameIdOrderByScoreDesc(getIdByUsername(username), gameID);
        if(result.size() > 0) {
            return result.get(0).getScore();
        }else{
            return Integer.MIN_VALUE;
        }
    }

    public int[] getRecentScoresByGame(String username, int gameID, int numberScores){
//        return ud.getUserRecentScores(username, gameID, numberScores);
        List<ScoreInfo> result = sr.findByUserIdAndGameIdOrderByScoreDateDescIdDesc(getIdByUsername(username), gameID);
        if(result.size()>numberScores){
            int[] output = new int[numberScores];
            for (int i = 0; i < numberScores; i++) {
                output[i] = result.get(i).getScore();
            }
            return output;
        }else{
            int[] output = new int[result.size()];
            for (int i = 0; i < result.size(); i++) {
                output[i] = result.get(i).getScore();
            }
            return output;
        }
    }

    private int getIdByUsername(String username){
        List<UserInfo> results = ur.findByUsername(username);
        if(results.size() > 0){
            return results.get(0).getId();
        }else{
            return 0;
        }
    }
}
