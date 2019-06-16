package com.flightapp.ticketing.service;

import com.flightapp.ticketing.domain.TicketDetails;

import java.util.Optional;

public interface TicketingApi {

    Optional<Boolean> isTicketAvailable(TicketDetails ticketDetails);
}
