package com.roilka.active.api.controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RedisController
 * @Description TODO
 * @Author zhanghui1
 * @Date 2019/11/20 20:28
 **/
@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    @RequestMapping("/testStringAndHash")
    @ResponseBody
    public Map<String, Object> testStringAndHash() {
        redisTemplate.opsForValue().set("key1", "Value1");
        //注意这里使用的是JDK的序列化器，所以Redis 保存时不是整数，不能运算
        redisTemplate.opsForValue().set("int_key", "1");
        stringRedisTemplate.opsForValue().set("int", "1");
        //使用运算
        stringRedisTemplate.opsForValue().increment("int", 1);

        //获取Jedis 底层连接
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.decr("int");
        Map<String, String> hash = new HashMap<>();
        hash.put("field1", "value1");
        hash.put("field2", "value2");
        hash.put("field3", "value3");
        //存入一个散列数据类型
        stringRedisTemplate.opsForHash().putAll("hash", hash);
        // 新增一个字段

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @RequestMapping("/testPipeLine")
    @ResponseBody
    public Map<String, Object> testPipeLine() {
        Long start = System.currentTimeMillis();
        List list = (List) redisTemplate.executePipelined((RedisOperations operations) -> {
            for (int i = 0; i <= 5000; i++) {
                operations.opsForValue().set("pipeline_" + i, "value_" + i);
                String value = (String) operations.opsForValue().get("pipeline_" + i);
                if (i==5000){
                    System.out.println("命令指示进入队列，所以值为空【"+value+"】");
                }
            }
            return null;
        });

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }
}
