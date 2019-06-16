package com.flightapp.baggage.service;

import com.flightapp.baggage.domain.BaggageCheckInRequest;
import com.flightapp.baggage.domain.BaggageCheckInResponse;
import com.flightapp.baggage.domain.BaggageDetails;
import com.flightapp.baggage.repository.BaggageRepository;
import com.flightapp.common.utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BaggageCheckInService implements BaggageCheckInApi {

    @Autowired
    private BaggageRepository baggageRepository;

    @Autowired
    private BaggageCache baggageCache;

    @Override
    public BaggageCheckInResponse processCheckingRequest(BaggageCheckInRequest baggageCheckInRequest) {
        return baggageCache.runWithSupplier(baggageCheckInRequest, checkInRequest -> {
            Optional<BaggageDetails> baggageDetailsResult = baggageRepository.findById(
                    Pair.of(checkInRequest.getDestinationId(), checkInRequest.getBaggageId()));
            return baggageDetailsResult.map(this::successResponse)
                    .orElse(failedCheckingResponse(checkInRequest));
        });
    }

    private BaggageCheckInResponse failedCheckingResponse(BaggageCheckInRequest baggageCheckInRequest) {
        return BaggageCheckInResponse.builder()
                .destinationId(baggageCheckInRequest.getDestinationId())
                .baggageId(baggageCheckInRequest.getBaggageId())
                .checkInSucceeded(true)
                .build();
    }

    private BaggageCheckInResponse successResponse(BaggageDetails baggageDetails) {
        return BaggageCheckInResponse.builder()
                .destinationId(baggageDetails.getDestinationId())
                .baggageId(baggageDetails.getBaggageId())
                .checkInSucceeded(false)
                .build();
    }


}
