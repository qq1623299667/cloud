package com.demo.redis.controller;

import com.demo.redis.util.DistributeLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author 石佳
 * @since 2020/06/19
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private DistributeLock distributeLock;

    @GetMapping("/test1")
    public String test1(){
        return distributeLock.runMethodWithLock("test1", UUID.randomUUID().toString(), () -> "test1执行成功");
    }
    @GetMapping("/test2")
    public String test2(){
        return distributeLock.runMethodWithLock("test2", UUID.randomUUID().toString(), () -> {
            Thread.sleep(3000L);
            return "test2执行成功";
        });
    }
}
