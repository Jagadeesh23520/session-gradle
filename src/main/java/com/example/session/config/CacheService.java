package com.example.session.config;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

	private final RedisTemplate<String, Object> redisTemplate;

	public CacheService(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	// Set cache
	public void setCache(String id, String key, Object value) {
		String setKey = id + key;
		redisTemplate.opsForValue().set(setKey, value, 300, TimeUnit.SECONDS);

	}

	// Get cache
	public Object getCache(String id, String key) {
		String setKey = id + key;
		return redisTemplate.opsForValue().get(setKey);
	}

	// Delete cache
	public void deleteCache(String key) {
		redisTemplate.delete(key);
	}
}
