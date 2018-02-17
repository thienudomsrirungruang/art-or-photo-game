package com.thien.service;

import com.thien.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAdder {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao ud;

    public void enterUser(String username, String password){
        String encodedPassword = passwordEncoder.encode(password);
        ud.enterNewUser(username, encodedPassword);
    }
}
