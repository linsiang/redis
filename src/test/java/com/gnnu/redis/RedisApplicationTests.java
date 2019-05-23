package com.gnnu.redis;

import com.gnnu.redis.entry.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisTemplate<Object, User> userRedisTemplate;
    @Test
    public void contextLoads() {
        List list  = new ArrayList();

    User user = new User();
    user.setId(100);
    user.setPwd("123456");
    user.setUname("nahs");
    userRedisTemplate.opsForValue().set("user",user);


    }

}
