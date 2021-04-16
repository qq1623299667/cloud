package com.demo.redis.redistemplate.config.spring.cache;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.util.concurrent.Callable;

/**
 * 自定义缓存
 * @author Jia Shi
 * @since 2020/12/8
 */
public class MyRedisCache extends RedisCache {
    /**
     * 一件开启关闭缓存
     * 用于排除缓存干扰进行调试
     * @since 2020/12/8
     */
    boolean useCache = true;

    /**
     * Create new {@link RedisCache}.
     *
     * @param name        must not be {@literal null}.
     * @param cacheWriter must not be {@literal null}.
     * @param cacheConfig must not be {@literal null}.
     */
    protected MyRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
        super(name, cacheWriter, cacheConfig);
    }

    /**
     * 如果redis的值为空，就不缓存
     * @author Jia Shi
     * @since 2020/12/3
     */
    @Override
    public void put(Object key, Object value) {
        // 如果关闭缓存，则完全绕过缓存
        if(!useCache){
            return;
        }
        Object cacheValue = preProcessCacheValue(value);

        if (!isAllowNullValues() && cacheValue == null) {
            return;
        }
        super.put(key, value);
    }

    /**
     * 获取数据
     * @author Jia Shi
     * @since 2020/12/8
     */
    @Override
    public synchronized <T> T get(Object key, Callable<T> valueLoader) {
        // 如果关闭缓存，则完全绕过缓存
        if(!useCache){
            T value = valueFromLoader(key, valueLoader);
            put(key, value);
            return value;
        }
        return super.get(key, valueLoader);
    }

    private static <T> T valueFromLoader(Object key, Callable<T> valueLoader) {

        try {
            return valueLoader.call();
        } catch (Exception e) {
            throw new ValueRetrievalException(key, valueLoader, e);
        }
    }
}
