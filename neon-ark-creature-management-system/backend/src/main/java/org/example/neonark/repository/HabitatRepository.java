package org.example.neonark.repository;

import org.example.neonark.entity.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitatRepository extends JpaRepository<Habitat, Long> {
}