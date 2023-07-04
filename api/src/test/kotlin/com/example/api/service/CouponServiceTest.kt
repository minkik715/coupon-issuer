package com.example.api.service

import com.example.api.repository.CouponRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
internal class CouponServiceTest @Autowired constructor(
     var couponService: CouponService,
     var couponRepository: CouponRepository
) {

    @Test
    fun 응모한개() {
        couponService.issue(1)
        Assertions.assertThat(couponRepository.count()).isEqualTo(1);
    }

    @Test
    fun 응모와장창() {

        val threadCount = 1000;

        val executorService = Executors.newFixedThreadPool(32);

        val latch = CountDownLatch(threadCount)

        for (i in 1..threadCount) {
            executorService.submit {
                try {
                    couponService.issue(i)
                }finally {
                    latch.countDown();
                }
            }
        }
        latch.await()
        Assertions.assertThat(couponRepository.count()).isEqualTo(100);
    }
}