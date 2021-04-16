package com.demo.redis.redistemplate.config;

import com.demo.redis.redistemplate.config.spring.cache.CommonRedisConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 石佳
 * @since 2020/06/22
 */
@Configuration
public class RedisSerializerConfig {
    @Bean("redisTemplate")
    public RedisTemplate<String, String> strRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(CommonRedisConfig.keySerializer());
        template.setValueSerializer(CommonRedisConfig.valueSerializer());
        template.setHashKeySerializer(CommonRedisConfig.keySerializer());
        template.setHashValueSerializer(CommonRedisConfig.valueSerializer());
        return template;
    }
}
