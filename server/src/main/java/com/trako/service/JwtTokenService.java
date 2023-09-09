package com.trako.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.trako.model.RefreshTokenDTO;


@Service
public class JwtTokenService {
	private final RedisTemplate<String, RefreshTokenDTO> redisTemplate;

    @Autowired
    public JwtTokenService(RedisTemplate<String, RefreshTokenDTO> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveToken(String userId, RefreshTokenDTO token,long expirationInSeconds) {
        String key = "user:" + userId + ":jwt";
        redisTemplate.opsForValue().set(key, token);
        //TTL 설정(만료 시간을 초 단위로 지정)
        redisTemplate.expire(key,  expirationInSeconds, TimeUnit.SECONDS);
    }

    public RefreshTokenDTO getToken(String userId) {
        String key = "user:" + userId + ":jwt";
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteToken(String userId) {
        String key = "user:" + userId + ":jwt";
        redisTemplate.delete(key);
    }
}
