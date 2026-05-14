package org.example.neonark.repository;

import org.example.neonark.entity.Creature;
import org.example.neonark.entity.FeedingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface FeedingScheduleRepository extends JpaRepository<FeedingSchedule, Long> {

    boolean existsByCreatureAndActiveTrue(Creature creature);

    List<FeedingSchedule> findByFeedingTimeAndActiveTrue(LocalTime feedingTime);
}