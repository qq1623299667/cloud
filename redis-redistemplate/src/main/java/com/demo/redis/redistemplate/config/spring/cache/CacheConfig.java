package com.demo.redis.redistemplate.config.spring.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * 缓存设置
 * @author Jia Shi
 * @since 2020/11/19
 */
@EnableCaching
@Configuration
public class CacheConfig {
    @Value("${redis.cluster.hostport:127.0.0.1}")
    private String nodes;
    @Value("${redis.cluster.passwd:123456}")
    private String password;
    @Value("${spring.application.name}")
    private String applicationName;

    @Primary
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 缓存配置对象
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();

        defaultCacheConfig = defaultCacheConfig.computePrefixWith(cacheName->"spring:cache:"+applicationName+":"+cacheName+":")
                .entryTtl(Duration.ofSeconds(CommonRedisConfig.DEFAULT_TTL))
                // 如果是空值，不缓存
                .disableCachingNullValues()
                // 设置key序列化器
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(CommonRedisConfig.keySerializer()))
                // 设置value序列化器
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer((CommonRedisConfig.valueSerializer())));

        return new EnhanceRedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),defaultCacheConfig);
    }

    // 自定义连接集群，如果有的话
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
//        Set<RedisNode> clusterNodes = getclusterNodes(nodes);
//        redisClusterConfiguration.setClusterNodes(clusterNodes);
//        redisClusterConfiguration.setPassword(RedisPassword.of(password));
//        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisClusterConfiguration);
//        return lettuceConnectionFactory;
//    }
//
//    private Set<RedisNode> getclusterNodes(String nodes) {
//        Set<RedisNode> clusterNodes = new HashSet<>();
//        String[] stringNodes = nodes.split(";");
//        for (String stringNode : stringNodes) {
//            String[] split = stringNode.split(":");
//            clusterNodes.add(new RedisNode(split[0], Integer.parseInt(split[1])));
//        }
//        return clusterNodes;
//    }
}
