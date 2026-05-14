package org.example.neonark.service;

import org.example.neonark.dto.FeedingLookupResponse;
import org.example.neonark.dto.FeedingResponse;
import org.example.neonark.entity.FeedingSchedule;
import org.example.neonark.exception.BadRequestException;
import org.example.neonark.repository.FeedingScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class FeedingService {

    private final FeedingScheduleRepository feedingScheduleRepository;

    public FeedingService(FeedingScheduleRepository feedingScheduleRepository) {
        this.feedingScheduleRepository = feedingScheduleRepository;
    }

    public FeedingLookupResponse findFeedingsByTime(String timeValue) {

        if (timeValue == null || timeValue.trim().isEmpty()) {
            throw new BadRequestException("Feeding time is required.");
        }

        LocalTime feedingTime;

        try {
            feedingTime = LocalTime.parse(timeValue.trim());
        }
        catch (DateTimeParseException ex) {
            throw new BadRequestException("Time must use HH:MM format.");
        }

        List<FeedingResponse> feedingResponses =
                feedingScheduleRepository.findByFeedingTimeAndActiveTrue(feedingTime)
                        .stream()
                        .map(this::toFeedingResponse)
                        .toList();

        String message;

        if (feedingResponses.isEmpty()) {
            message = "No active feeding schedules found for the requested time.";
        }
        else {
            message = "Feeding schedules retrieved successfully.";
        }

        return new FeedingLookupResponse(
                message,
                feedingResponses
        );
    }

    private FeedingResponse toFeedingResponse(FeedingSchedule feedingSchedule) {

        return new FeedingResponse(
                feedingSchedule.getCreature().getCreatureId(),
                feedingSchedule.getCreature().getName(),
                feedingSchedule.getCreature().getHabitat().getHabitatName(),
                feedingSchedule.getFeedingTime(),
                feedingSchedule.getFoodType(),
                feedingSchedule.getNotes()
        );
    }
}