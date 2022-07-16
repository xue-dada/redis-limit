package com.example.redislimit.controller;

import com.example.redislimit.service.RedisTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RedisTestController {
    @Autowired
    private RedisTestService redisTestService;
    @PostMapping("/test")
    public String testRedis() {
        redisTestService.testRedis();
        return "123";
    }
}
