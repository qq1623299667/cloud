package com.demo.redis.redistemplate.service;

import java.util.List;

/**
 *
 * @author Jia Shi
 * @since 2020/11/19
 */
public interface ITestService {
    List<String> list() throws InterruptedException;

    String one(Long id) throws InterruptedException;
}
