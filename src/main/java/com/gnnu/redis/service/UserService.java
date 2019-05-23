package com.gnnu.redis.service;

import com.gnnu.redis.entry.User;
import com.gnnu.redis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;
/*    @Autowired
    RedisTemplate<Object, User> redisTemplate;*/
    public List<User> userList() {
        List<User> list = new ArrayList<>();
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
           list = (List<User>) redisTemplate.opsForValue().get("list");

        synchronized (this) {
            list = (List<User>) redisTemplate.opsForValue().get("list");
            if (list == null) {
                list = userMapper.userList();
                redisTemplate.opsForValue().set("list",list);
                System.out.println("从数据库那。。。。");
            } else {
                System.out.println("缓存。。。。。");
            }
        }
        return list;
    }
}
