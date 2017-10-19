package com.frost.webworm.webmagic.service;

import com.frost.webworm.webmagic.entity.User;
import com.frost.webworm.webmagic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LB on 2017/10/19.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList(){
        return userMapper.getUserList();
    }
}
