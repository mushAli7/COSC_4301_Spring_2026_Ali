package org.example.neonark.service;

import org.example.neonark.dto.CreatureObservationsResponse;
import org.example.neonark.dto.CreatureResponse;
import org.example.neonark.dto.ObservationResponse;
import org.example.neonark.entity.Creature;
import org.example.neonark.entity.Observation;
import org.example.neonark.exception.BadRequestException;
import org.example.neonark.exception.NotFoundException;
import org.example.neonark.repository.CreatureRepository;
import org.example.neonark.repository.ObservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObservationService {

    private final CreatureRepository creatureRepository;
    private final ObservationRepository observationRepository;
    private final CreatureService creatureService;

    public ObservationService(
            CreatureRepository creatureRepository,
            ObservationRepository observationRepository,
            CreatureService creatureService
    ) {
        this.creatureRepository = creatureRepository;
        this.observationRepository = observationRepository;
        this.creatureService = creatureService;
    }

    public CreatureObservationsResponse getCreatureObservations(Long creatureId) {

        if (creatureId == null || creatureId <= 0) {
            throw new BadRequestException("Creature ID must be a positive number.");
        }

        Creature creature = creatureRepository.findById(creatureId)
                .orElseThrow(() -> new NotFoundException("Creature ID was not found."));

        CreatureResponse creatureResponse =
                creatureService.getCreatureById(creatureId);

        List<ObservationResponse> observations =
                observationRepository.findByCreatureOrderByObservedAtAsc(creature)
                        .stream()
                        .map(this::toObservationResponse)
                        .toList();

        return new CreatureObservationsResponse(
                creatureResponse,
                observations
        );
    }

    private ObservationResponse toObservationResponse(Observation observation) {

        return new ObservationResponse(
                observation.getObservationId(),
                observation.getUser().getFullName(),
                observation.getUser().getUsername(),
                observation.getObservationText(),
                observation.getObservedAt()
        );
    }
}