package com.gnnu.redis.config;

import com.gnnu.redis.entry.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class MyConfig {
         @Bean
    public  RedisTemplate<Object, User> userRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
             RedisTemplate <Object, User> template = new RedisTemplate <Object, User>();
             template.setConnectionFactory(redisConnectionFactory);
             Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<User>(User.class);
             template.setDefaultSerializer(jackson2JsonRedisSerializer);
             return template;
         }
}
