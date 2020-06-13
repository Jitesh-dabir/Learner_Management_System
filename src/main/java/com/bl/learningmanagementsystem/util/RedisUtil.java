package com.bl.learningmanagementsystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Component
public class RedisUtil implements Serializable, IRedisUtil{

    private static final long serialVersionUID = 1L;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     *
     * @param RedisKey
     * @param email
     * @param authenticationToken
     * Save token for user
     */
    @Override
    public void save(String RedisKey, String email, String authenticationToken) {
        redisTemplate.opsForHash().put(RedisKey, email, authenticationToken);
    }

    /**
     *
     * @param redisKey
     * @return all token
     */
    @Override
    public Map<Object, Object> getAll(String redisKey) {
        return redisTemplate.opsForHash().entries(redisKey);
    }

    /**
     *
     * @param redisKey
     * @param userId
     * @return Token
     */
    @Override
    public Map<Object, Object> getToken(String redisKey, String userId) {
        return (Map<Object, Object>) redisTemplate.opsForHash().get(redisKey, userId);
    }
}
