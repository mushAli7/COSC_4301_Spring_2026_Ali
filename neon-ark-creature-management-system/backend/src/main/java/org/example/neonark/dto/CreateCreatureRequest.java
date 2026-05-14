package org.example.neonark.dto;

public record CreateCreatureRequest(
        String name,
        String species,
        String dangerLevel,
        String status,
        String notes,
        Long habitatId
) {
}