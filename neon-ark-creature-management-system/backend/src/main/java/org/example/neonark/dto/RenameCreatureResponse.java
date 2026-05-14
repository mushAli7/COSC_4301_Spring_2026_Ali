package org.example.neonark.dto;

public record RenameCreatureResponse(
        Long creatureId,
        String oldName,
        String newName,
        String message
) {
}