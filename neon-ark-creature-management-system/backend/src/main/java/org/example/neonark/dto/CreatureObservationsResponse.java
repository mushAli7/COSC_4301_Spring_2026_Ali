package org.example.neonark.dto;

import java.util.List;

public record CreatureObservationsResponse(
        CreatureResponse creature,
        List<ObservationResponse> observations
) {
}