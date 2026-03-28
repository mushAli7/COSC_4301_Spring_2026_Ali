package org.example.neonarkintaketracker.controller;

import org.example.neonarkintaketracker.entity.Creature;
import org.example.neonarkintaketracker.service.CreatureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/creatures")
public class CreatureController {

    private final CreatureService creatureService;

    public CreatureController(CreatureService creatureService) {
        this.creatureService = creatureService;
    }

    // Returns all creatures
    @GetMapping
    public List<Creature> getAllCreatures() {
        return creatureService.getAllCreatures();
    }

    // Returns one creature by id
    @GetMapping("/{id}")
    public ResponseEntity<Creature> getCreatureById(@PathVariable Long id) {
        Optional<Creature> creature = creatureService.getCreatureById(id);

        if (creature.isPresent()) {
            return ResponseEntity.ok(creature.get());
        }

        return ResponseEntity.notFound().build();
    }

    // Creates a new creature
    @PostMapping
    public ResponseEntity<Creature> createCreature(@RequestBody Creature creature) {
        Creature savedCreature = creatureService.saveCreature(creature);
        return new ResponseEntity<>(savedCreature, HttpStatus.CREATED);
    }
}