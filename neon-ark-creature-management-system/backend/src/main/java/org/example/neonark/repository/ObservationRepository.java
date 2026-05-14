package org.example.neonark.repository;

import org.example.neonark.entity.Creature;
import org.example.neonark.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, Long> {

    List<Observation> findByCreatureOrderByObservedAtAsc(Creature creature);
}