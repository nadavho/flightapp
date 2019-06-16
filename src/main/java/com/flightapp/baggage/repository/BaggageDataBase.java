package com.flightapp.baggage.repository;

import com.flightapp.baggage.domain.BaggageDetails;
import com.flightapp.common.utils.Pair;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class BaggageDataBase {

    Table<Long, String, BaggageDetails> baggageDetailsTable = HashBasedTable.create();

    @PostConstruct
    private void init() {
        addBaggageDetails(1, "a");
        addBaggageDetails(2, "b");
        addBaggageDetails(3, "c");
        addBaggageDetails(4, "d");
        addBaggageDetails(5, "e");
        addBaggageDetails(6, "f");
    }

    private void addBaggageDetails(long destinationId, String baggageId) {
        saveBaggage(BaggageDetails.builder()
                .baggageId(baggageId)
                .destinationId(destinationId)
                .build());
    }

    public void saveBaggage(BaggageDetails baggageDetails) {
        baggageDetailsTable.put(baggageDetails.getDestinationId(), baggageDetails.getBaggageId(), baggageDetails);
    }

    public Optional<BaggageDetails> findBaggage(Pair<Long, String> baggageIdentifier) {
        return Optional.ofNullable(baggageDetailsTable.get(baggageIdentifier.getKey(), baggageIdentifier.getValue()));
    }
}
