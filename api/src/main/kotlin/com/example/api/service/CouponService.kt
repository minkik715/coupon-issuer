package com.example.api.service

import com.example.api.domain.Coupon
import com.example.api.repository.CouponRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponService(
    private val couponRepository: CouponRepository
) {

    /*
    스프링은 자체적으로 스레드 풀을 가지고 있어 request가 들어오면
    스레드를 스레드 풀에서 꺼내서 사용하게 한다.
    즉 짧은 시간내에 많은 요청을 들어오면
    coupon 이 save 되기전에 여러개의 count를 셀 수 있게 된다.
    즉 여러 쓰레드에서 count가 99일 수 있다는 것이다. 그러면 쿠폰 발급을 초과해 큰 문제가 생기게 된다.
    따라서 이 데이터 경주 상황을 해결하기 위해 여러가지 방법이 있을 수 있다.
    첫번 째로 비관적 락을 걸어 coupon 을 읽고 업데이트 하는 동안 다른 쓰레드에서 접근하지 못하게 락을 거는 방법이 있다. -> 해당 로직이 길어질 경우 굉장히 느려질 수있다.
    둘 째로 sync를 사용하여 하나의 쓰레드에서만 해당 함수를 접근 할 수 있게 한다. 이또한 함수 자체의 로직이 길다면 문제가 생길 수 있다.
    셋 째로 낙관적 락을 걸어 update version을 사용하는 것이다. 이는 실패시 새로운 로직을 추가해줘야하고 선착순과는 좀 거리가 있다고 볼 수 있다.
    넷째로 redis를 사용하는 것이다 redis는 싱글 쓰레드를 사용하는데  coupon count를 셀 때 레디스를 사용하여 세면 동시에 들어오지 못한다?? --> 문제 해결 필요...
     마지막으로 메세지 큐를 사용하는 방법이 있다.
     */

    fun issue(userId : Int) {
        val count = couponRepository.count()
        if(count > 100) {
            return
        }
        couponRepository.save(Coupon(userId =userId))
        return
    }
}