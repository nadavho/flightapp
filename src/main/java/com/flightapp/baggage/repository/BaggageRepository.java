package com.flightapp.baggage.repository;

import com.flightapp.common.repository.DataRepository;
import com.flightapp.common.utils.Pair;
import com.flightapp.baggage.domain.BaggageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BaggageRepository implements DataRepository<BaggageDetails, Pair<Long,String>> {

    @Autowired
    private BaggageDataBase baggageDataBase;

    @Override
    public void save(BaggageDetails baggageDetails) {
        baggageDataBase.saveBaggage(baggageDetails);
    }

    @Override
    public Optional<BaggageDetails> findById(Pair<Long, String> baggageIdentifier) {
        return baggageDataBase.findBaggage(baggageIdentifier);
    }
}
