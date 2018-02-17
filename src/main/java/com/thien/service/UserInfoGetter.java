package com.thien.service;

import com.thien.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoGetter {

    @Autowired
    private UserDao ud;

    public int getPlayCount(String username){
        return ud.getPlayCountByUsername(username);
    }
}
