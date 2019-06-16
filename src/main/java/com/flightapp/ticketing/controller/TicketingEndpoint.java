package com.flightapp.ticketing.controller;

import com.flightapp.ticketing.domain.TicketDetails;
import com.flightapp.ticketing.service.TicketingApi;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/ticketing")
@Log4j2
public class TicketingEndpoint {

    @Autowired
    private TicketingApi ticketingApi;

    @RequestMapping(value = "available/{ticketId}", method = RequestMethod.GET)
    public boolean isTicketAvailabe(@PathVariable long ticketId) {
        TicketDetails ticketDetails = TicketDetails.builder().ticketId(ticketId).build();
        log.debug("Verifying ticket availability for {}", ticketDetails);
        boolean ticketAvailable = ticketingApi
                .isTicketAvailable(ticketDetails)
                .orElse(false);
        log.debug("Availability status {} for {}", ticketAvailable, ticketDetails);
        return ticketAvailable;
    }
}
