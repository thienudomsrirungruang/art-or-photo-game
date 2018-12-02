package com.thien.service;

import com.thien.dao.UserDao;
import com.thien.entity.UserInfo;
import com.thien.entity.UserRoleInfo;
import com.thien.repository.UserInfoRepository;
import com.thien.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserAdderTest {

    @Mock
    UserDao ud;

    @Mock
    UserRepository ur;

    @Mock
    UserInfoRepository uir;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserAdder userAdder;

    @Test
    public void checkUserExistsTest(){
        userAdder.checkUserExists("");
        verify(ur, times(1)).findByUsername(Matchers.anyString());
    }

    @Test
    public void enterUserTest(){
        when(passwordEncoder.encode(Matchers.anyString())).thenReturn("");
        userAdder.enterUser("", "");
        verify(passwordEncoder, times(1)).encode(Matchers.anyString());
        verify(ur, times(1)).save(Matchers.any(UserInfo.class));
        verify(uir, times(1)).save(Matchers.any(UserRoleInfo.class));
    }

}
