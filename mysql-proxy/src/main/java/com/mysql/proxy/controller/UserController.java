package com.mysql.proxy.controller;

import com.mysql.proxy.domain.User;
import com.mysql.proxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shijia
 * @date 2019-11-20 14:23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> userList() {
        return userService.listUser();
    }

    @RequestMapping("/update")
    public int update() {
        return userService.update();
    }
}
