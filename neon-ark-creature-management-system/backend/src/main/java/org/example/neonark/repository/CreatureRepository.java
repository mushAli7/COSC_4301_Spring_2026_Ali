package org.example.neonark.repository;

import org.example.neonark.entity.Creature;
import org.example.neonark.entity.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreatureRepository extends JpaRepository<Creature, Long> {

    boolean existsByHabitatAndNameIgnoreCase(Habitat habitat, String name);

    Optional<Creature> findByCreatureId(Long creatureId);
}