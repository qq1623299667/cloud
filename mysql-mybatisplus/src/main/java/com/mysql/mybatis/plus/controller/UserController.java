package com.mysql.mybatis.plus.controller;


import com.mysql.mybatis.plus.model.User;
import com.mysql.mybatis.plus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 石佳
 * @since 2020-07-24
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    public Long create(@RequestBody User user){
        Long id = userService.createUser(user);
        return id;
    }
}

