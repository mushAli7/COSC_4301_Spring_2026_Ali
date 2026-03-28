package org.example.neonarkintaketracker.service;

import org.example.neonarkintaketracker.entity.Creature;
import org.example.neonarkintaketracker.repository.CreatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreatureService {

    private final CreatureRepository creatureRepository;

    public CreatureService(CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }

    // Returns all creatures from the database
    public List<Creature> getAllCreatures() {
        return creatureRepository.findAll();
    }

    // Returns one creature by id if found
    public Optional<Creature> getCreatureById(Long id) {
        return creatureRepository.findById(id);
    }

    // Saves a new creature to the database
    public Creature saveCreature(Creature creature) {
        creature.setCreatedAt(java.time.LocalDateTime.now());
        return creatureRepository.save(creature);
    }
}