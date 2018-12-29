package com.thien.service;

import com.thien.entity.HighScoreInfo;
import com.thien.entity.ScoreInfo;
import com.thien.repository.ScoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ScoreGetterTest {

    @Mock
    ScoreRepository sr;

    @InjectMocks
    ScoreService scoreGetter;

    @Test
    public void weeklyTopScoreExistsTest(){
        ScoreInfo si = Mockito.mock(ScoreInfo.class);
        when(sr.findWeeklyTopScoreFromDate(Matchers.any(Date.class), Matchers.any(int.class))).thenReturn(new ArrayList<>(Arrays.asList(si)));
        ScoreInfo result = scoreGetter.getWeeklyTopScore(1);
        assertNotNull(result);
        verify(sr, times(1)).findWeeklyTopScoreFromDate(Matchers.any(Date.class), Matchers.any(int.class));
    }

    @Test
    public void weeklyTopScoreNullTest(){
        when(sr.findWeeklyTopScoreFromDate(Matchers.any(Date.class), Matchers.any(int.class))).thenReturn(new ArrayList<>());
        ScoreInfo result = scoreGetter.getWeeklyTopScore(1);
        assertNull(result);
        verify(sr, times(1)).findWeeklyTopScoreFromDate(Matchers.any(Date.class), Matchers.any(int.class));
    }

    @Test
    public void enterScoreTest(){
        when(sr.save(Matchers.any(ScoreInfo.class))).thenReturn(null);
        scoreGetter.enterScore(1, 1, 0);
        verify(sr, times(1)).save(any(ScoreInfo.class));
    }

    @Test
    public void getGlobalHighScoreExistsTest(){
        ScoreInfo si = Mockito.mock(ScoreInfo.class);
        when(si.getScore()).thenReturn(0);
        when(sr.findByGameIdOrderByScoreDesc(Matchers.any(int.class))).thenReturn(new ArrayList<ScoreInfo>(Arrays.asList(si)));
        HighScoreInfo hs = scoreGetter.getGlobalHighScore(1);
        assertTrue(hs.isHasScore());
        verify(sr, times(1)).findByGameIdOrderByScoreDesc(Matchers.any(int.class));
        verify(si, times(1)).getScore();
    }

    @Test
    public void getGlobalHighScoreDoesntExistTest(){
        when(sr.findByGameIdOrderByScoreDesc(Matchers.any(int.class))).thenReturn(new ArrayList<ScoreInfo>());
        HighScoreInfo hs = scoreGetter.getGlobalHighScore(1);
        assertFalse(hs.isHasScore());
        verify(sr, times(1)).findByGameIdOrderByScoreDesc(Matchers.any(int.class));
    }

}
