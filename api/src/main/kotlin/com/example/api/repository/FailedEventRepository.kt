package com.example.api.repository

import com.example.api.domain.Coupon
import com.example.api.domain.FailedEvent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FailedEventRepository : JpaRepository<FailedEvent, String>