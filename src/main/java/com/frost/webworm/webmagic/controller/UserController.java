package com.frost.webworm.webmagic.controller;

import com.frost.webworm.webmagic.entity.User;
import com.frost.webworm.webmagic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by LB on 2017/10/19.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/usertest")
    public List<User> getUserList(){
        return userService.getUserList();
    }
}
