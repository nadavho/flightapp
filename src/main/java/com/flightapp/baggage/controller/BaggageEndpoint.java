package com.flightapp.baggage.controller;

import com.flightapp.baggage.domain.BaggageCheckInRequest;
import com.flightapp.baggage.domain.BaggageCheckInResponse;
import com.flightapp.baggage.service.BaggageCheckInApi;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/baggage")
@Log4j2
public class BaggageEndpoint {

    @Autowired
    private BaggageCheckInApi baggageCheckInApi;

    @RequestMapping(value = "checkIn", method = RequestMethod.POST)
    public boolean checkinBaggage(@RequestParam("destinationId") long destinationId,
                                  @RequestParam("baggageId") String baggageId) {
        BaggageCheckInRequest baggageCheckInRequest = BaggageCheckInRequest.builder()
                .destinationId(destinationId)
                .baggageId(baggageId)
                .build();
        log.debug("Starting checking process for {}", baggageCheckInRequest);
        BaggageCheckInResponse baggageCheckInResponse = baggageCheckInApi.processCheckingRequest(baggageCheckInRequest);
        log.debug("Done processing: {}", baggageCheckInResponse);
        return baggageCheckInResponse.isCheckInSucceeded();
    }
}
