package com.bl.learningmanagementsystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Component
public class RedisUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void save(String RedisKey, String email, String authenticationToken) {
        redisTemplate.opsForHash().put(RedisKey, email, authenticationToken);
    }

    public Map<Object, Object> getAll(String redisKey) {
        return redisTemplate.opsForHash().entries(redisKey);
    }
}
