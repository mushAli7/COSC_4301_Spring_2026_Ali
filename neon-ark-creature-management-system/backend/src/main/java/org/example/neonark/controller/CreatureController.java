package org.example.neonark.controller;

import org.example.neonark.dto.*;
import org.example.neonark.service.CreatureService;
import org.example.neonark.service.ObservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Handles creature management API routes used by the CLI.
 * Each route maps directly to one required menu action.
 */
@RestController
@RequestMapping("/api/creatures")
public class CreatureController {

    private final CreatureService creatureService;
    private final ObservationService observationService;

    public CreatureController(
            CreatureService creatureService,
            ObservationService observationService
    ) {
        this.creatureService = creatureService;
        this.observationService = observationService;
    }

    @GetMapping
    public ResponseEntity<List<CreatureResponse>> listAllCreatures() {
        return ResponseEntity.ok(creatureService.listAllCreatures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatureResponse> getCreatureById(@PathVariable Long id) {
        return ResponseEntity.ok(creatureService.getCreatureById(id));
    }

    @PostMapping
    public ResponseEntity<CreatureResponse> createCreature(@RequestBody CreateCreatureRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creatureService.createCreature(request));
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<RenameCreatureResponse> renameCreature(
            @PathVariable Long id,
            @RequestBody RenameCreatureRequest request
    ) {
        return ResponseEntity.ok(creatureService.renameCreature(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> removeCreature(@PathVariable Long id) {
        return ResponseEntity.ok(creatureService.removeCreature(id));
    }

    @GetMapping("/{id}/observations")
    public ResponseEntity<CreatureObservationsResponse> getCreatureObservations(@PathVariable Long id) {
        return ResponseEntity.ok(observationService.getCreatureObservations(id));
    }
}