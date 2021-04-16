package com.demo.redis.redistemplate.config.spring.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;

/**
 * 增强spring boot redis缓存管理器
 * @author Jia Shi
 * @since 2020/11/19
 */
public class EnhanceRedisCacheManager extends RedisCacheManager {
    private RedisCacheWriter cacheWriter;
    private RedisCacheConfiguration defaultCacheConfig;

    public EnhanceRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration){
        super(cacheWriter, defaultCacheConfiguration);
        this.cacheWriter = cacheWriter;
        this.defaultCacheConfig = defaultCacheConfiguration;
    }

    /**
     * 配置让带#的启用自定义过期时间
     * @param name 缓存名字
     * @param cacheConfig 缓存配置
     * @return org.springframework.data.redis.cache.RedisCache redis缓存
     * @author Jia Shi
     * @since 2020/11/19
     */
    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        if(name.contains("#")){
            String[] split = name.split("#");
            String str = split[split.length - 1];
            if(StringUtils.isNumeric(str)){
                long ttl = Long.parseLong(str);
                name = name.substring(0,name.lastIndexOf("#"));
                cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(ttl));
            }
        }
        return new MyRedisCache(name, cacheWriter, cacheConfig != null ? cacheConfig : defaultCacheConfig);
    }
}
