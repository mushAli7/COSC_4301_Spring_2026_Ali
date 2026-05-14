package org.example.neonark.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "feeding_schedules")
public class FeedingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feeding_schedule_id")
    private Long feedingScheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creature_id", nullable = false)
    private Creature creature;

    @Column(name = "feeding_time", nullable = false)
    private LocalTime feedingTime;

    @Column(name = "food_type", nullable = false, length = 120)
    private String foodType;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Column(columnDefinition = "TEXT")
    private String notes;
}