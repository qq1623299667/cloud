package com.demo.redis.redistemplate.controller;

import com.demo.redis.redistemplate.entity.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录相关接口
 * @author Jia Shi
 * @since 2020/12/23
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    /**
     * 登录接口
     * @author Jia Shi
     * @since 2020/12/23
     */
    @PostMapping("/login")
    public Result login(String userId){
        return new Result(userId);
    }
}
