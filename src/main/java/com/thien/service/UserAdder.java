package com.thien.service;

import com.thien.dao.UserDao;
import com.thien.entity.UserInfo;
import com.thien.entity.UserRoleInfo;
import com.thien.repository.UserInfoRepository;
import com.thien.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserAdder {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao ud;

    @Autowired
    private UserRepository ur;

    @Autowired
    private UserInfoRepository uir;

    public boolean checkUserExists(String username){
//        return ud.checkUserExists(username);
        return ur.findByUsername(username).size() > 0;
    }

    @Transactional
    public void enterUser(String username, String password){
        String encodedPassword = passwordEncoder.encode(password);
//        ud.enterNewUser(username, encodedPassword);
        UserInfo record = new UserInfo();
        record.setUsername(username);
        record.setPassword(encodedPassword);
        record.setEnabled(true);
        ur.save(record);

        UserRoleInfo roleRecord = new UserRoleInfo();
        roleRecord.setRole("user");
        roleRecord.setUserId(getIdByUsername(username));
        uir.save(roleRecord);
    }

    private int getIdByUsername(String username){
        List<UserInfo> results = ur.findByUsername(username);
        if(results.size() > 0){
            return results.get(0).getId();
        }else{
            return 0;
        }
    }
}
