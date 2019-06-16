package com.flightapp.coupon.service;

import com.flightapp.coupon.domain.CouponRequest;
import com.flightapp.coupon.domain.CouponResponse;

public interface CouponApi {

    CouponResponse processCoupon(CouponRequest couponRequest);
}
