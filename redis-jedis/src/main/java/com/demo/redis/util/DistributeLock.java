package com.demo.redis.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;

/**
 * @author 石佳
 * @since 2020/06/19
 */
@Component
@Slf4j
public class DistributeLock {
    private Jedis jedis;
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.database}")
    private Integer database;
    @Value("${spring.redis.password}")
    private String password;

    @PostConstruct
    public void init(){
        initDistributeLock(host,port,database,password);
    }

    /**
    *  初始化分布式锁的连接
    * @param host redis服务器
    * @param port 端口
    * @param database 数据库
    * @param password 密码
    * @author 石佳
    * @since 2020/6/22
    */
    public void initDistributeLock(String host, int port, Integer database, String password) {
        jedis = new Jedis(host, port);
        if (!StringUtils.isEmpty(password)) {
            jedis.auth(password.trim());
        }
        if (database == null || database < 0 || database > 15) {
            database = 0;
        }
        jedis.select(database);
        log.info(">>> jedis初始化结果 {} <<<",jedis!=null);
    }

    @FunctionalInterface
    public interface DistributedMethod<T> {
        T call() throws InterruptedException;
    }

    /**
     *  执行分布式锁的方法
     * @param key 锁
     * @param requestId 请求标识
     * @param expireTime 锁的过期时间,单位：毫秒
     * @author 石佳
     * @since 2020/6/22
     */
    public <T> T runMethodWithLock(String key, String requestId,int expireTime, DistributedMethod<T> method){
        T result = null;
        boolean locked = false;
        try {
            locked = RedisTool.tryGetDistributedLock(jedis, key, requestId, expireTime);
            log.info(">>> 当前适用的分布式锁的key是 {} ,requestId是 {} ,获取锁的结果 {} <<<",key,requestId,locked);
            if(locked){
                result = method.call();
            }
        }catch (Exception e){
            log.error(">>> 执行分布式锁的方法时发生异常，报错信息是 {} <<<",e.getMessage());
        }finally {
            if(locked){
                boolean releaseLockResult = releaseKey(key, requestId);
                log.info(">>> 分布式锁 {} 解锁结果 {} <<<",key,releaseLockResult);
            }
        }
        return result;
    }

    /**
    *  执行分布式锁的方法，默认锁的过期时间5秒
    * @param key 锁
    * @param requestId 请求标识
    * @author 石佳
    * @since 2020/6/19
    */
    public <T> T runMethodWithLock(String key, String requestId, DistributedMethod<T> method){
        return runMethodWithLock(key,requestId,5000,method);
    }

    /**
     * 释放分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public boolean releaseKey(String lockKey, String requestId){
        boolean release = false;
        try {
            release = RedisTool.releaseDistributedLock(jedis, lockKey, requestId);
        }catch (Exception e){
            log.error(">>> 释放分布式锁时发生异常，报错信息是 {} <<<",e.getMessage());
        }
        return release;
    }
}
