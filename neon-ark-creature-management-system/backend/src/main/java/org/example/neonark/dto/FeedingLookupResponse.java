package org.example.neonark.dto;

import java.util.List;

public record FeedingLookupResponse(
        String message,
        List<FeedingResponse> feedings
) {
}