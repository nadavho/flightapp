package com.flightapp.coupon.controller;

import com.flightapp.coupon.domain.CouponRequest;
import com.flightapp.coupon.domain.CouponResponse;
import com.flightapp.coupon.service.CouponApi;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/coupon")
@Log4j2
public class CouponEndpoint {

    @Autowired
    private CouponApi couponApi;

    @RequestMapping(value = "process", method = RequestMethod.POST)
    public CouponResponse processCoupon(@RequestParam("couponId") long couponId,
                                        @RequestParam("price") double price) {
        CouponRequest couponRequest = CouponRequest.builder()
                .couponId(couponId)
                .price(price)
                .build();
        log.debug("Processing {}", couponRequest);
        CouponResponse couponResponse = couponApi.processCoupon(couponRequest);
        log.debug("Coupon result {}", couponResponse);
        return couponResponse;
    }
}
