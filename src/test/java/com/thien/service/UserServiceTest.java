package com.thien.service;

import com.thien.dao.UserDao;
import com.thien.entity.ScoreInfo;
import com.thien.entity.UserInfo;
import com.thien.entity.UserRoleInfo;
import com.thien.repository.ScoreRepository;
import com.thien.repository.UserInfoRepository;
import com.thien.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository ur;

    @Mock
    ScoreRepository sr;

    @Mock
    UserDao ud;

    @Mock
    UserInfoRepository uir;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;



    @Test
    public void userExistsFalseTest(){
        when(ur.findByUsername(Matchers.anyString())).thenReturn(new ArrayList<>());
        boolean result = userService.userExists("");
        assertFalse(result);
        verify(ur, times(1)).findByUsername(Matchers.anyString());
    }

    @Test
    public void userExistsTrueTest(){
        UserInfo userInfo = Mockito.mock(UserInfo.class);
        when(ur.findByUsername(Matchers.anyString())).thenReturn(new ArrayList<>(Collections.singletonList(userInfo)));
        boolean result = userService.userExists("");
        assertTrue(result);
        verify(ur, times(1)).findByUsername(Matchers.anyString());
    }

    @Test
    public void getPlayCountTest(){
        UserInfo userInfo = Mockito.mock(UserInfo.class);
        when(userInfo.getId()).thenReturn(1);
        when(sr.findByUserId(Matchers.anyInt())).thenReturn(new ArrayList<>(Collections.singletonList(new ScoreInfo())));
        when(ur.findByUsername(Matchers.anyString())).thenReturn(new ArrayList<>(Collections.singletonList(userInfo)));
        int result = userService.getPlayCount("");
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
        int result = userService.getPlayCountByGame("", 1);
        assertEquals(result, 1);
        verify(ur, times(1)).findByUsername(Matchers.anyString());
        verify(sr, times(1)).findByUserIdAndGameId(Matchers.anyInt(), Matchers.anyInt());
    }

    @Test
    public void getHighScoreByGameExistsTest(){
        ScoreInfo topScore = Mockito.mock(ScoreInfo.class);
        when(topScore.getScore()).thenReturn(1);
        when(sr.findByUserIdAndGameIdOrderByScoreDesc(Matchers.anyInt(), Matchers.anyInt())).thenReturn(new ArrayList<>(Collections.singletonList(topScore)));
        int result = userService.getHighScoreByGame("", 1);
        assertEquals(result, 1);
        verify(sr, times(1)).findByUserIdAndGameIdOrderByScoreDesc(Matchers.anyInt(), Matchers.anyInt());
    }

    @Test
    public void getHighScoreByGameNotExistTest(){
        when(sr.findByUserIdAndGameIdOrderByScoreDesc(Matchers.anyInt(), Matchers.anyInt())).thenReturn(new ArrayList<>());
        int result = userService.getHighScoreByGame("", 1);
        assertEquals(result, Integer.MIN_VALUE);
        verify(sr, times(1)).findByUserIdAndGameIdOrderByScoreDesc(Matchers.anyInt(), Matchers.anyInt());
    }

    @Test
    public void getRecentScoresByGameExcessTest(){
        ScoreInfo scoreInfo = Mockito.mock(ScoreInfo.class);
        when(scoreInfo.getScore()).thenReturn(1);
        when(sr.findByUserIdAndGameIdOrderByScoreDateDescIdDesc(Matchers.anyInt(), Matchers.anyInt())).thenReturn(new ArrayList<>(Arrays.asList(scoreInfo, scoreInfo, scoreInfo)));
        int[] result = userService.getRecentScoresByGame("", 1, 2);
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
        int[] result = userService.getRecentScoresByGame("", 1, 4);
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
        int result = userService.getIdByUsername("");
        assertEquals(result, 1);
        verify(ur, times(1)).findByUsername(Matchers.anyString());
    }

    @Test
    public void getIdByUsernameNotExistTest(){
        when(ur.findByUsername(Matchers.anyString())).thenReturn(new ArrayList<>());
        int result = userService.getIdByUsername("");
        assertEquals(result, 0);
        verify(ur, times(1)).findByUsername(Matchers.anyString());
    }

    @Test
    public void getUsernameByIdExistsTest(){
        UserInfo userInfo = Mockito.mock(UserInfo.class);
        when(userInfo.getUsername()).thenReturn("test");
        when(ur.findById(Matchers.anyInt())).thenReturn(new ArrayList<>(Collections.singletonList(userInfo)));
        String result = userService.getUsernameById(1);
        assertEquals(result, "test");
        verify(ur, times(1)).findById(Matchers.anyInt());
    }

    @Test
    public void getUsernameByIdNotExistTest(){
        when(ur.findById(Matchers.anyInt())).thenReturn(new ArrayList<>());
        String result = userService.getUsernameById(1);
        assertEquals(result, null);
        verify(ur, times(1)).findById(Matchers.anyInt());
    }

    @Test
    public void checkUserExistsTest(){
        userService.checkUserExists("");
        verify(ur, times(1)).findByUsername(Matchers.anyString());
    }

    @Test
    public void enterUserTest(){
        when(passwordEncoder.encode(Matchers.anyString())).thenReturn("");
        userService.enterUser("", "");
        verify(passwordEncoder, times(1)).encode(Matchers.anyString());
        verify(ur, times(1)).save(Matchers.any(UserInfo.class));
        verify(uir, times(1)).save(Matchers.any(UserRoleInfo.class));
    }
}
