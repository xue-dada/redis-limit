package com.example.redislimit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 参考
 * https://www.codenong.com/jsc37787f6d333/
 * https://juejin.cn/post/6844904147112706062
 */

@Service
public class RedisTestService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void testRedis() {

        String script = "local key=KEYS[1]\n" +
                "local ttl=ARGV[1]\n" +
                "local maxCount=tonumber(ARGV[2])\n" +
                "if redis.call('EXISTS', key) == 0 then\n" +
                "\tredis.call('SETEX', key, ttl, '1')\n" +
                "\treturn 1\n" +
                "else \n" +
                "\tlocal count = tonumber(redis.call('INCR', key))\n" +
                "\tif count > maxCount then \n" +
                "\t\treturn -1\n" +
                "\tend\n" +
                "\treturn count\n" +
                "end";
        System.out.println(script);
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        Long test1 = redisTemplate.execute(redisScript, Collections.singletonList("test1"), "10", "2000");
        System.out.println("++" + test1);
    }
}
