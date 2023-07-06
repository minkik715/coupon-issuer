package com.example.api.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class IssuedUserRepository(
    private val redisTemplate: RedisTemplate<String, Long>
) {

    fun issueCoupon(userId: Long): Long? {
        return redisTemplate.opsForSet()
            .add("coupon_issued_user_id", userId)
    }
}