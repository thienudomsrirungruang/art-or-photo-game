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
}
