package com.thien.service;

import com.thien.entity.ScoreInfo;
import com.thien.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ScoreGetter {

    @Autowired
    private ScoreRepository sr;

    public ScoreInfo getWeeklyTopScore(int gameId){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        List<ScoreInfo> result =  sr.findWeeklyTopScoreFromDate(c.getTime(), gameId);
        return result.size() == 0 ? null : result.get(0);
    }

}
