package com.flightapp.baggage.service;

import com.flightapp.baggage.domain.BaggageCheckInRequest;
import com.flightapp.baggage.domain.BaggageCheckInResponse;

public interface BaggageCheckInApi {

    BaggageCheckInResponse processCheckingRequest(BaggageCheckInRequest baggageCheckInRequest);
}
