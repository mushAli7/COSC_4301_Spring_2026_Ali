package org.example.neonark.dto;

import java.time.LocalDateTime;

public record ObservationResponse(
        Long observationId,
        String authorName,
        String authorUsername,
        String observationText,
        LocalDateTime observedAt
) {
}