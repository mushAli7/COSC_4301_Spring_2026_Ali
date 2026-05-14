package org.example.neonark.controller;

import org.example.neonark.dto.FeedingLookupResponse;
import org.example.neonark.service.FeedingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Handles feeding lookup requests used by the CLI.
 */
@RestController
@RequestMapping("/api/feedings")
public class FeedingController {

    private final FeedingService feedingService;

    public FeedingController(FeedingService feedingService) {
        this.feedingService = feedingService;
    }

    @GetMapping
    public ResponseEntity<FeedingLookupResponse> findFeedingsByTime(@RequestParam String time) {
        return ResponseEntity.ok(feedingService.findFeedingsByTime(time));
    }
}