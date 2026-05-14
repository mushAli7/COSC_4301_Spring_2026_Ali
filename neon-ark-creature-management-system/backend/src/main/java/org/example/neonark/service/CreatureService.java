package org.example.neonark.service;

import org.example.neonark.dto.*;
import org.example.neonark.entity.Creature;
import org.example.neonark.entity.Habitat;
import org.example.neonark.exception.BadRequestException;
import org.example.neonark.exception.ConflictException;
import org.example.neonark.exception.NotFoundException;
import org.example.neonark.repository.CreatureRepository;
import org.example.neonark.repository.FeedingScheduleRepository;
import org.example.neonark.repository.HabitatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatureService {

    private final CreatureRepository creatureRepository;
    private final HabitatRepository habitatRepository;
    private final FeedingScheduleRepository feedingScheduleRepository;

    public CreatureService(
            CreatureRepository creatureRepository,
            HabitatRepository habitatRepository,
            FeedingScheduleRepository feedingScheduleRepository
    ) {
        this.creatureRepository = creatureRepository;
        this.habitatRepository = habitatRepository;
        this.feedingScheduleRepository = feedingScheduleRepository;
    }

    public List<CreatureResponse> listAllCreatures() {
        return creatureRepository.findAll()
                .stream()
                .map(this::toCreatureResponse)
                .toList();
    }

    public CreatureResponse getCreatureById(Long creatureId) {
        Creature creature = findCreatureOrThrow(creatureId);
        return toCreatureResponse(creature);
    }

    public CreatureResponse createCreature(CreateCreatureRequest request) {
        validateCreateRequest(request);

        Habitat habitat = habitatRepository.findById(request.habitatId())
                .orElseThrow(() -> new BadRequestException("Habitat ID does not exist."));

        if (creatureRepository.existsByHabitatAndNameIgnoreCase(habitat, request.name().trim())) {
            throw new ConflictException("A creature with this name already exists in that habitat.");
        }

        Creature creature = Creature.builder()
                .name(request.name().trim())
                .species(request.species().trim())
                .dangerLevel(request.dangerLevel().trim().toUpperCase())
                .status(request.status().trim().toUpperCase())
                .notes(cleanOptionalText(request.notes()))
                .habitat(habitat)
                .createdAt(java.time.LocalDateTime.now())
                .build();

        Creature savedCreature = creatureRepository.save(creature);
        return toCreatureResponse(savedCreature);
    }

    public RenameCreatureResponse renameCreature(Long creatureId, RenameCreatureRequest request) {
        if (request == null || isBlank(request.newName())) {
            throw new BadRequestException("New creature name is required.");
        }

        Creature creature = findCreatureOrThrow(creatureId);
        String oldName = creature.getName();
        String newName = request.newName().trim();

        if (oldName.equalsIgnoreCase(newName)) {
            throw new BadRequestException("New creature name must be different from the current name.");
        }

        if (creatureRepository.existsByHabitatAndNameIgnoreCase(creature.getHabitat(), newName)) {
            throw new ConflictException("A creature with this name already exists in that habitat.");
        }

        creature.setName(newName);
        Creature savedCreature = creatureRepository.save(creature);

        return new RenameCreatureResponse(
                savedCreature.getCreatureId(),
                oldName,
                savedCreature.getName(),
                "Creature renamed successfully."
        );
    }

    public MessageResponse removeCreature(Long creatureId) {
        Creature creature = findCreatureOrThrow(creatureId);

        if ("REMOVED".equalsIgnoreCase(creature.getStatus())) {
            throw new ConflictException("Creature is already marked as REMOVED.");
        }

        if (feedingScheduleRepository.existsByCreatureAndActiveTrue(creature)) {
            throw new ConflictException("Creature cannot be removed while an active feeding schedule exists.");
        }

        creature.setStatus("REMOVED");
        creatureRepository.save(creature);

        return new MessageResponse("Creature status changed to REMOVED.");
    }

    private Creature findCreatureOrThrow(Long creatureId) {
        if (creatureId == null || creatureId <= 0) {
            throw new BadRequestException("Creature ID must be a positive number.");
        }

        return creatureRepository.findById(creatureId)
                .orElseThrow(() -> new NotFoundException("Creature ID was not found."));
    }

    private CreatureResponse toCreatureResponse(Creature creature) {
        return new CreatureResponse(
                creature.getCreatureId(),
                creature.getName(),
                creature.getSpecies(),
                creature.getDangerLevel(),
                creature.getStatus(),
                creature.getHabitat().getHabitatName(),
                creature.getNotes(),
                creature.getCreatedAt()
        );
    }

    private void validateCreateRequest(CreateCreatureRequest request) {
        if (request == null) {
            throw new BadRequestException("Creature request is required.");
        }

        if (isBlank(request.name())) {
            throw new BadRequestException("Creature name is required.");
        }

        if (isBlank(request.species())) {
            throw new BadRequestException("Species is required.");
        }

        if (isBlank(request.dangerLevel())) {
            throw new BadRequestException("Danger level is required.");
        }

        if (isBlank(request.status())) {
            throw new BadRequestException("Status is required.");
        }

        if (request.habitatId() == null || request.habitatId() <= 0) {
            throw new BadRequestException("Habitat ID must be a positive number.");
        }

        String dangerLevel = request.dangerLevel().trim().toUpperCase();
        if (!dangerLevel.equals("LOW") && !dangerLevel.equals("MEDIUM") && !dangerLevel.equals("HIGH")) {
            throw new BadRequestException("Danger level must be LOW, MEDIUM, or HIGH.");
        }

        String status = request.status().trim().toUpperCase();
        if (!status.equals("ACTIVE")
                && !status.equals("QUARANTINED")
                && !status.equals("CRITICAL")
                && !status.equals("REMOVED")) {
            throw new BadRequestException("Status must be ACTIVE, QUARANTINED, CRITICAL, or REMOVED.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private String cleanOptionalText(String value) {
        if (isBlank(value)) {
            return null;
        }

        return value.trim();
    }
}