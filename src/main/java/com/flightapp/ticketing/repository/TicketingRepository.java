package com.flightapp.ticketing.repository;

import com.flightapp.common.repository.DataRepository;
import com.flightapp.ticketing.domain.TicketDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TicketingRepository implements DataRepository<TicketDetails,Long> {

    @Autowired
    private TicketingDatabase ticketingDatabase;

    @Override
    public void save(TicketDetails ticketDetails) {
        ticketingDatabase.saveTicket(ticketDetails);
    }

    @Override
    public Optional<TicketDetails> findById(Long ticketId) {
        return ticketingDatabase.findTicket(ticketId);
    }


}
