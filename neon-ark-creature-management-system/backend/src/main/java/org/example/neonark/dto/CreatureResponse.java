package org.example.neonark.dto;

import java.time.LocalDateTime;

public record CreatureResponse(
        Long creatureId,
        String name,
        String species,
        String dangerLevel,
        String status,
        String habitatName,
        String notes,
        LocalDateTime createdAt
) {
}