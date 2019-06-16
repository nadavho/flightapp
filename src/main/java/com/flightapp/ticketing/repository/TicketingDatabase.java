package com.flightapp.ticketing.repository;

import com.flightapp.ticketing.domain.TicketDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class TicketingDatabase {

    private Set<TicketDetails> ticketsTable = new HashSet<>();

    @PostConstruct
    private void init() {
        addTicket(1);
        addTicket(2);
        addTicket(3);
        addTicket(4);
        addTicket(5);
        addTicket(6);
    }

    private void addTicket(long id) {
        ticketsTable.add(TicketDetails.builder()
                .ticketId(id)
                .build());
    }

    public void saveTicket(TicketDetails ticketDetails) {
        ticketsTable.add(ticketDetails);
    }

    public Optional<TicketDetails> findTicket(long ticketId) {
        return ticketsTable.stream()
                .filter(ticketDetails -> ticketDetails.getTicketId() == ticketId)
                .findFirst();
    }
}
