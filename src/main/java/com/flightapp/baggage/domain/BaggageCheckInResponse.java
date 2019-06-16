package com.flightapp.baggage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaggageCheckInResponse {

    private long destinationId;
    private String baggageId;
    private boolean checkInSucceeded;
}
