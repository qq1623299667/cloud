package com.demo.redis.redistemplate.controller;

import com.demo.redis.redistemplate.service.ITestService;
import com.demo.redis.redistemplate.util.DistributeLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author 石佳
 * @since 2020/06/24
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ITestService testService;
    @Resource
    private DistributeLock distributeLock;

    @GetMapping("/test1")
    public String test1(){
        return distributeLock.runMethodWithLock("test1",UUID.randomUUID().toString(),()-> {
            Thread.sleep(3000L);
            return "加锁成功！";
        });
    }

    @GetMapping("/test2")
    public String test2(){
        return distributeLock.runMethodWithLock("test1",UUID.randomUUID().toString(),()-> "加锁成功！");
    }

    @GetMapping("/aa")
    public List<String> list() throws InterruptedException {
        log.info(">>> aa <<<");
        return testService.list();
    }

    @GetMapping("/bb/{id}")
    public String one(@PathVariable("id") Long id) throws InterruptedException {
        log.info(">>> bb <<<");
        return testService.one(id);
    }

    /**
     * 测试缓存
     * @author Jia Shi
     * @since 2020/12/8
     */
    @GetMapping("/testCache")
    @Cacheable(value = "testCache")
    public String testCache() throws InterruptedException {
        log.info(">>> 开始测试缓存  <<<");
        Thread.sleep(3000L);
        return "success";
    }
}
