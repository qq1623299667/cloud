package com.demo.redis.redistemplate.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author 石佳
 * @since 2020/06/22
 */
@Slf4j
@Component
public class DistributeLock {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @FunctionalInterface
    public interface DistributedMethod<T> {
        T call() throws InterruptedException;
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
    *  执行分布式锁的方法
    * @param key 锁
    * @param requestId 请求标识
    * @param expireTime 锁的过期时间,单位：毫秒
    * @return T
    * @author 石佳
    * @since 2020/6/24
    */
    public <T> T runMethodWithLock(String key, String requestId,int expireTime, DistributedMethod<T> method){
        T result = null;
        boolean locked = false;
        try {
            locked = getDistributeLock(redisTemplate, key, requestId, expireTime);
            log.info(">>> 当前适用的分布式锁的key是 {} ,requestId是 {} ,获取锁的结果 {} <<<",key,requestId,locked);
            if(locked){
                result = method.call();
            }
        }catch (Exception e){
            log.error(">>> 执行分布式锁的方法时发生异常，报错信息是 {} <<<",e.getMessage());
        }finally {
            if(locked){
                boolean releaseLockResult = releaseDistributeLock(redisTemplate,key, requestId);
                log.info(">>> 分布式锁 {} 解锁结果 {} <<<",key,releaseLockResult);
            }
        }
        return result;
    }

    /**
     * 通过lua脚本 加锁并设置过期时间
     *
     * @param key    锁key值
     * @param value  锁value值
     * @param expire 过期时间，单位毫秒
     * @return true：加锁成功，false：加锁失败
     */
    private boolean getDistributeLock(RedisTemplate<String, String> redisTemplate, String key, String value, int expire) {
        String strScript = "if redis.call('setNx',KEYS[1],ARGV[1])==1 then return redis.call('pexpire',KEYS[1],ARGV[2]) else return 0 end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptText(strScript);
        try {
            Long result = redisTemplate.execute(redisScript, Collections.singletonList(key), value, expire);
            log.info(">>> ip:[{}] get lock:[{}], value:[{}], getLock result:[{}] <<<", IpUtil.getLocalIpAddr(), key, value, result);
            return result != null && result == 1;
        } catch (Exception e) {
            //可以自己做异常处理
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 通过lua脚本释放锁
     *
     * @param key   锁key值
     * @param value 锁value值（仅当redis里面的value值和传入的相同时才释放，避免释放其他线程的锁）
     * @return true：释放锁成功，false：释放锁失败（可能已过期或者已被释放）
     */
    private boolean releaseDistributeLock(RedisTemplate<String, String> redisTemplate, String key, String value) {
        String strScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptText(strScript);
        try {
            Long result = redisTemplate.execute(redisScript, Collections.singletonList(key), value);
            log.info(">>> ip:[{}] release lock:[{}], value:[{}], release result: [{}] <<<", IpUtil.getLocalIpAddr(), key, value, result);
            return result != null && result == 1;
        } catch (Exception e) {
            //可以自己做异常处理
            log.error(e.getMessage(), e);
            return false;
        }
    }
}
