package com.flightapp.ticketing.service;

import com.flightapp.ticketing.domain.TicketDetails;
import com.flightapp.ticketing.repository.TicketingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketingService implements TicketingApi {

    @Autowired
    private TicketingRepository ticketingRepository;

    @Autowired
    private TicketingCache ticketingCache;

    @Override
    public Optional<Boolean> isTicketAvailable(TicketDetails ticketDetails) {
        return ticketingCache.runWithSupplier(ticketDetails, td ->
                ticketingRepository
                        .findById(ticketDetails.getTicketId())
                        .map(found -> true)
        );
    }
}
