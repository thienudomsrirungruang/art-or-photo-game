package com.thien.service;

import com.thien.entity.HighScoreInfo;
import com.thien.entity.PictureInfo;
import com.thien.entity.ScoreInfo;
import com.thien.repository.PictureRepository;
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
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScoreAdderTest {

    @Mock
    ScoreRepository sr;

    @InjectMocks
    ScoreAdder scoreAdder;

    @Test
    public void enterScoreTest(){
        when(sr.save(Matchers.any(ScoreInfo.class))).thenReturn(null);
        scoreAdder.enterScore(1, 1, 0);
        verify(sr, times(1)).save(any(ScoreInfo.class));
    }

    @Test
    public void getGlobalHighScoreExistsTest(){
        ScoreInfo si = Mockito.mock(ScoreInfo.class);
        when(si.getScore()).thenReturn(0);
        when(sr.findByGameIdOrderByScoreDesc(Matchers.any(int.class))).thenReturn(new ArrayList<ScoreInfo>(Arrays.asList(si)));
        HighScoreInfo hs = scoreAdder.getGlobalHighScore(1);
        assertTrue(hs.isHasScore());
        verify(sr, times(1)).findByGameIdOrderByScoreDesc(Matchers.any(int.class));
        verify(si, times(1)).getScore();
    }

    @Test
    public void getGlobalHighScoreDoesntExistTest(){
        when(sr.findByGameIdOrderByScoreDesc(Matchers.any(int.class))).thenReturn(new ArrayList<ScoreInfo>());
        HighScoreInfo hs = scoreAdder.getGlobalHighScore(1);
        assertFalse(hs.isHasScore());
        verify(sr, times(1)).findByGameIdOrderByScoreDesc(Matchers.any(int.class));
    }
}