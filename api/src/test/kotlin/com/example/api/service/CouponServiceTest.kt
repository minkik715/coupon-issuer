package com.example.api.service

import com.example.api.repository.CouponRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class CouponServiceTest @Autowired constructor(
     var couponService: CouponService,
     var couponRepository: CouponRepository
) {

    @Test
    fun 응모와장창() {
        couponService.issue(1)
        Assertions.assertThat(couponRepository.count()).isEqualTo(1);
    }
}