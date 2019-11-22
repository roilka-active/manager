package com.roilka.active.api;

import com.roilka.active.api.controller.redis.RedisController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiApplicationTests {

    @Autowired
    private RedisController redisController;
    @Test
    void contextLoads() {
        redisController.testPipeLine();
        redisController.testList();
        redisController.testStringAndHash();
    }

}
