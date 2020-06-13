package com.bl.learningmanagementsystem.util;

import java.util.Map;

public interface IRedisUtil {
    public void save(String RedisKey, String email, String authenticationToken);

    public Map<Object, Object> getAll(String redisKey);

    public Map<Object, Object> getToken(String redisKey, String userId);
}
