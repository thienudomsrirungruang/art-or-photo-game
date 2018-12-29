package com.thien.service;

import com.thien.entity.ScoreInfo;
import com.thien.entity.UserInfo;
import com.thien.repository.ScoreRepository;
import com.thien.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserInfoGetterTest {

    @Mock
    UserRepository ur;

    @Mock
    ScoreRepository sr;

    @InjectMocks
    UserInfoGetter userInfoGetter;

    @Test
    public void userExistsFalseTest(){
        when(ur.findByUsername(Matchers.anyString())).thenReturn(new ArrayList<>());
        boolean result = userInfoGetter.userExists("");
        assertFalse(result);
        verify(ur, times(1)).findByUsername(Matchers.anyString());
    }

    @Test
    public void userExistsTrueTest(){
        UserInfo userInfo = Mockito.mock(UserInfo.class);
        when(ur.findByUsername(Matchers.anyString())).thenReturn(new ArrayList<>(Collections.singletonList(userInfo)));
        boolean result = userInfoGetter.userExists("");
        assertTrue(result);
        verify(ur, times(1)).findByUsername(Matchers.anyString());
    }

    @Test
    public void getPlayCountTest(){
        UserInfo userInfo = Mockito.mock(UserInfo.class);
        when(userInfo.getId()).thenReturn(1);
        when(sr.findByUserId(Matchers.anyInt())).thenReturn(new ArrayList<>(Collections.singletonList(new ScoreInfo())));
        when(ur.findByUsername(Matchers.anyString())).thenReturn(new ArrayList<>(Collections.singletonList(userInfo)));
        int result = userInfoGetter.getPlayCount("");
        assertEquals(result, 1);
        verify(ur, times(1)).findByUsername(Matchers.anyString());
        verify(sr, times(1)).findByUserId(Matchers.anyInt());
    }

    @Test
    public void getPlayCountByGameTest(){
        UserInfo userInfo = Mockito.mock(UserInfo.class);
        when(userInfo.getId()).thenReturn(1);
        when(sr.findByUserIdAndGameId(Matchers.anyInt(), Matchers.anyInt())).thenReturn(new ArrayList<>(Collections.singletonList(new ScoreInfo())));
        when(ur.findByUsername(Matchers.anyString())).thenReturn(new ArrayList<>(Collections.singletonList(userInfo)));
        int result = userInfoGetter.getPlayCountByGame("", 1);
        assertEquals(result, 1);
        verify(ur, times(1)).findByUsername(Matchers.anyString());
        verify(sr, times(1)).findByUserIdAndGameId(Matchers.anyInt(), Matchers.anyInt());
    }

    @Test
    public void getHighScoreByGameExistsTest(){
        ScoreInfo topScore = Mockito.mock(ScoreInfo.class);
        when(topScore.getScore()).thenReturn(1);
        when(sr.findByUserIdAndGameIdOrderByScoreDesc(Matchers.anyInt(), Matchers.anyInt())).thenReturn(new ArrayList<>(Collections.singletonList(topScore)));
        int result = userInfoGetter.getHighScoreByGame("", 1);
        assertEquals(result, 1);
        verify(sr, times(1)).findByUserIdAndGameIdOrderByScoreDesc(Matchers.anyInt(), Matchers.anyInt());
    }

    @Test
    public void getHighScoreByGameNotExistTest(){
        when(sr.findByUserIdAndGameIdOrderByScoreDesc(Matchers.anyInt(), Matchers.anyInt())).thenReturn(new ArrayList<>());
        int result = userInfoGetter.getHighScoreByGame("", 1);
        assertEquals(result, Integer.MIN_VALUE);
        verify(sr, times(1)).findByUserIdAndGameIdOrderByScoreDesc(Matchers.anyInt(), Matchers.anyInt());
    }

    @Test
    public void getRecentScoresByGameExcessTest(){
        ScoreInfo scoreInfo = Mockito.mock(ScoreInfo.class);
        when(scoreInfo.getScore()).thenReturn(1);
        when(sr.findByUserIdAndGameIdOrderByScoreDateDescIdDesc(Matchers.anyInt(), Matchers.anyInt())).thenReturn(new ArrayList<>(Arrays.asList(scoreInfo, scoreInfo, scoreInfo)));
        int[] result = userInfoGetter.getRecentScoresByGame("", 1, 2);
        assertEquals(result.length, 2);
        for (int i : result) {
            assertEquals(i, 1);
        }
        verify(sr, times(1)).findByUserIdAndGameIdOrderByScoreDateDescIdDesc(Matchers.anyInt(), Matchers.anyInt());
    }

    @Test
    public void getRecentScoresByGameShortageTest(){
        ScoreInfo scoreInfo = Mockito.mock(ScoreInfo.class);
        when(scoreInfo.getScore()).thenReturn(1);
        when(sr.findByUserIdAndGameIdOrderByScoreDateDescIdDesc(Matchers.anyInt(), Matchers.anyInt())).thenReturn(new ArrayList<>(Arrays.asList(scoreInfo, scoreInfo, scoreInfo)));
        int[] result = userInfoGetter.getRecentScoresByGame("", 1, 4);
        assertEquals(result.length, 3);
        for (int i : result) {
            assertEquals(i, 1);
        }
        verify(sr, times(1)).findByUserIdAndGameIdOrderByScoreDateDescIdDesc(Matchers.anyInt(), Matchers.anyInt());
    }

    @Test
    public void getIdByUsernameExistsTest(){
        UserInfo userInfo = Mockito.mock(UserInfo.class);
        when(userInfo.getId()).thenReturn(1);
        when(ur.findByUsername(Matchers.anyString())).thenReturn(new ArrayList<>(Collections.singletonList(userInfo)));
        int result = userInfoGetter.getIdByUsername("");
        assertEquals(result, 1);
        verify(ur, times(1)).findByUsername(Matchers.anyString());
    }

    @Test
    public void getIdByUsernameNotExistTest(){
        when(ur.findByUsername(Matchers.anyString())).thenReturn(new ArrayList<>());
        int result = userInfoGetter.getIdByUsername("");
        assertEquals(result, 0);
        verify(ur, times(1)).findByUsername(Matchers.anyString());
    }

    @Test
    public void getUsernameByIdExistsTest(){
        UserInfo userInfo = Mockito.mock(UserInfo.class);
        when(userInfo.getUsername()).thenReturn("test");
        when(ur.findById(Matchers.anyInt())).thenReturn(new ArrayList<>(Collections.singletonList(userInfo)));
        String result = userInfoGetter.getUsernameById(1);
        assertEquals(result, "test");
        verify(ur, times(1)).findById(Matchers.anyInt());
    }

    @Test
    public void getUsernameByIdNotExistTest(){
        when(ur.findById(Matchers.anyInt())).thenReturn(new ArrayList<>());
        String result = userInfoGetter.getUsernameById(1);
        assertEquals(result, null);
        verify(ur, times(1)).findById(Matchers.anyInt());
    }
}
