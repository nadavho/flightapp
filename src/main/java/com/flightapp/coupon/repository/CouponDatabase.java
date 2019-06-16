package com.flightapp.coupon.repository;

import com.flightapp.coupon.domain.CouponDetails;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CouponDatabase {

    private List<Double> discounts = Lists.newArrayList(0.1, 0.5, 0.6);

    public Optional<CouponDetails> findCoupon(Long couponId) {
        return generateRandomCoupons()
                .stream().filter(couponDetails -> couponDetails.getCouponId() == couponId)
                .findFirst();
    }

    private List<CouponDetails> generateRandomCoupons() {
        int bound = (int) (Math.random() * 100) + 1;
        return IntStream.range(0, bound)
                .map(id -> id + bound)
                .boxed()
                .map(id -> CouponDetails.builder()
                        .couponId(id)
                        .discount(randomDiscountIndex())
                        .build())
                .collect(Collectors.toList());

    }

    private double randomDiscountIndex() {
        return discounts.get((int) (Math.random() * discounts.size()));
    }
}
