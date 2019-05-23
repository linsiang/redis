package com.gnnu.redis.controller;

import com.gnnu.redis.entry.User;
import com.gnnu.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class IndexController {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserService userService;

    @GetMapping("/{path}")
    public String toPath(@PathVariable String path) {
        return path;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> userlist() {


        List<User> list  = userService.userList();

        return list;
    }
    @ResponseBody
    @GetMapping("/testUsers")
    public List<User> testUsers(){
        //模仿并发编程
        ExecutorService executorService = Executors.newFixedThreadPool(25);

        for(int i = 0;i<10000;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                      userService.userList();
                }
            });
        }
        return userService.userList();
    }

}
