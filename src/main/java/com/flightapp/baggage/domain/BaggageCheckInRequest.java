package com.flightapp.baggage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaggageCheckInRequest {

    private long destinationId;
    private String baggageId;
}
