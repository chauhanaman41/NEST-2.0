package com.pv.followup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PriorityService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String calculatePriority(String reporterId) {
        // Mock logic: Check if reporter has history in Redis
        // In real app: redisTemplate.opsForValue().get("history:" + reporterId)
        
        // Simulating logic: if reporterId starts with "DOC", they are high priority
        if (reporterId != null && reporterId.startsWith("DOC")) {
            return "HIGH";
        }
        return "MEDIUM";
    }
}
