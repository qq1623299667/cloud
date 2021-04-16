package com.demo.redis.redistemplate.config.spring.cache;

import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 通用序列化设置
 * @author Jia Shi
 * @since 2020/11/19
 */
public class CommonRedisConfig {
    public static long DEFAULT_TTL = 30*60L;
    private static RedisSerializer<String> keySerializer;
    private static RedisSerializer<Object> valueSerializer;

    public static RedisSerializer<String> keySerializer() {
        if(keySerializer==null){
            synchronized (CommonRedisConfig.class){
                if(keySerializer==null){
                    keySerializer = new StringRedisSerializer();
                }
            }
        }
        return keySerializer;
    }

    public static RedisSerializer<Object> valueSerializer() {
        if(valueSerializer==null){
            synchronized (CommonRedisConfig.class){
                if(valueSerializer==null){
                    valueSerializer = new GenericJackson2JsonRedisSerializer();
                }
            }
        }
        return valueSerializer;
    }
}
