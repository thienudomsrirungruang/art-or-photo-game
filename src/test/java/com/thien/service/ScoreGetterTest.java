package com.thien.service;

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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ScoreGetterTest {

    @Mock
    ScoreRepository sr;

    @InjectMocks
    ScoreGetter scoreGetter;

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

}
