package com.demo.redis.redistemplate.service.impl;

import com.demo.redis.redistemplate.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jia Shi
 * @since 2020/11/19
 */
@Slf4j
@Service
public class TestServiceImpl implements ITestService {
    @Cacheable(value = "user#10000")
    @Override
    public List<String> list() throws InterruptedException {
        Thread.sleep(3000L);
        log.info(">>> 进入到list <<<");
        List<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("bbb");
        return strings;
    }

    @Cacheable(value = "user",key = "#id")
    @Override
    public String one(Long id) throws InterruptedException {
        if(id.equals(1L)){
            return null;
        }
        Thread.sleep(3000L);
        log.info(">>> 请求参数 {} <<<",id);
        return String.valueOf(id);
    }
}
