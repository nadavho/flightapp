package com.flightapp.coupon.repository;

import com.flightapp.common.repository.DataRepository;
import com.flightapp.coupon.domain.CouponDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CouponRepository implements DataRepository<CouponDetails,Long> {

    @Autowired
    private CouponDatabase couponDatabase;

    @Override
    public void save(CouponDetails entity) {
    }

    @Override
    public Optional<CouponDetails> findById(Long couponId) {
        return couponDatabase.findCoupon(couponId);
    }
}
