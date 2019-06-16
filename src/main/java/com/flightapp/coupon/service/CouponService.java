package com.flightapp.coupon.service;

import com.flightapp.coupon.domain.CouponDetails;
import com.flightapp.coupon.domain.CouponRequest;
import com.flightapp.coupon.domain.CouponResponse;
import com.flightapp.coupon.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService implements CouponApi{

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public CouponResponse processCoupon(CouponRequest couponRequest) {
        return couponRepository.findById(couponRequest.getCouponId())
                .map(couponDetails -> buildSuccessfulCouponResponse(couponDetails,couponRequest))
                .orElse(buildFailedCouponResponse(couponRequest));
    }

    private CouponResponse buildSuccessfulCouponResponse(CouponDetails couponDetails, CouponRequest couponRequest) {
        return CouponResponse
                .builder()
                .valid(true)
                .price(couponRequest.getPrice() * couponDetails.getDiscount())
                .build();

    }

    private CouponResponse buildFailedCouponResponse(CouponRequest couponRequest) {
        return CouponResponse.builder()
                .valid(false)
                .price(couponRequest.getPrice())
                .build();
    }
}
