package com.tss.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: MQG
 * @date: 2018/12/7
 */
@Configuration
public class RedisConfig {
    
    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        initRedisTemplate(redisTemplate, connectionFactory);
        return redisTemplate;
    }

    /**
     * 设置key、value存入redis的序列化方式(jdk)
     *
     * @param redisTemplate
     * @param connectionFactory
     */
    private void initRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory connectionFactory) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
    }

    /**
     * 设置key、value存入redis的序列化方式(json)
     *
     * @param redisTemplate
     * @param connectionFactory
     */
//    private void initRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory connectionFactory) {
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<>(Object.class));
//        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
//        redisTemplate.setConnectionFactory(connectionFactory);
//    }

}
