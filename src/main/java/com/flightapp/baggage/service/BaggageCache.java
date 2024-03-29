package com.flightapp.baggage.service;

import com.flightapp.baggage.domain.BaggageCheckInRequest;
import com.flightapp.baggage.domain.BaggageCheckInResponse;
import com.flightapp.common.utils.cache.AbstractCache;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class BaggageCache extends AbstractCache<BaggageCheckInRequest, BaggageCheckInResponse> {

    @PostConstruct
    private void init() {
        cacheEvacuator.monitor(this, 10, TimeUnit.SECONDS);
    }
}
