package com.roilka.active.common.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName RedisTest
 * @Author Roilka
 * @Description redis测试
 * @Date 2019/11/20
 */
public class RedisTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForHash().put("hash", "h1", "hValue");
        System.out.println("key1 :" + redisTemplate.opsForValue().get("key1"));
        System.out.println("key1 :" + redisTemplate.opsForHash().get("hash","h1"));
    }
}
