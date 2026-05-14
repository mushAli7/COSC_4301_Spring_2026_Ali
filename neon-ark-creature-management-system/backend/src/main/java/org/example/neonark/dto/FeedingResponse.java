package org.example.neonark.dto;

import java.time.LocalTime;

public record FeedingResponse(
        Long creatureId,
        String creatureName,
        String habitatName,
        LocalTime feedingTime,
        String foodType,
        String notes
) {
}