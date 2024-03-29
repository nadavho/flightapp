package com.flightapp.ticketing.service;

import com.flightapp.common.utils.cache.AbstractCache;
import com.flightapp.ticketing.domain.TicketDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class TicketingCache extends AbstractCache<TicketDetails, Optional<Boolean>> {

    @PostConstruct
    private void init() {
        cacheEvacuator.monitor(this, 10, TimeUnit.SECONDS);
    }
}
