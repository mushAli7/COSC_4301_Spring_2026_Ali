-- =====================================================
-- Neon Ark Creature Management System
-- V2 - Operational Tables
--
-- This migration adds feeding schedules and observations.
-- These tables support feeding lookups, soft-delete conflict
-- checks, and creature observation history.
-- =====================================================

-- =====================================================
-- Feeding schedules store the times when creatures must be
-- fed. The remove route checks this table before allowing a
-- creature to be marked REMOVED.
-- =====================================================
CREATE TABLE feeding_schedules (
                                   feeding_schedule_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                   creature_id BIGINT NOT NULL,
                                   feeding_time TIME NOT NULL,
                                   food_type VARCHAR(120) NOT NULL,
                                   is_active BOOLEAN NOT NULL DEFAULT TRUE,
                                   notes TEXT,

                                   CONSTRAINT fk_feeding_schedules_creature
                                       FOREIGN KEY (creature_id)
                                           REFERENCES creatures(creature_id),

                                   CONSTRAINT uq_creature_feeding_time
                                       UNIQUE (creature_id, feeding_time)
);

-- =====================================================
-- Observations preserve staff notes about creature behavior,
-- condition, or safety concerns. They are historical records
-- and should remain attached to the creature over time.
-- =====================================================
CREATE TABLE observations (
                              observation_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                              creature_id BIGINT NOT NULL,
                              user_id BIGINT NOT NULL,
                              observation_text TEXT NOT NULL,
                              observed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                              CONSTRAINT fk_observations_creature
                                  FOREIGN KEY (creature_id)
                                      REFERENCES creatures(creature_id),

                              CONSTRAINT fk_observations_user
                                  FOREIGN KEY (user_id)
                                      REFERENCES users(user_id)
);