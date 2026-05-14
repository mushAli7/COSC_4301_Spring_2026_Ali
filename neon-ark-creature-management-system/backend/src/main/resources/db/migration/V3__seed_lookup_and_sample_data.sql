-- =====================================================
-- Neon Ark Creature Management System
-- V3 - Seed Data
--
-- Inserts sample operational data for testing routes,
-- joins, CLI output, and business-rule behavior.
-- =====================================================

-- =====================================================
-- Roles
-- =====================================================
INSERT INTO roles (role_name) VALUES
                                  ('ADMIN'),
                                  ('RESEARCH'),
                                  ('FIELD'),
                                  ('CARETAKER');

-- =====================================================
-- Users
-- =====================================================
INSERT INTO users (
    full_name,
    email,
    phone,
    username
) VALUES
      (
          'Ava Morgan',
          'ava.morgan@neonark.org',
          '512-555-1001',
          'avamorgan'
      ),
      (
          'Elias Reed',
          'elias.reed@neonark.org',
          '512-555-1002',
          'eliasreed'
      ),
      (
          'Mira Sol',
          'mira.sol@neonark.org',
          '512-555-1003',
          'mirasol'
      );

-- =====================================================
-- User Roles
-- =====================================================
INSERT INTO user_roles (user_id, role_id) VALUES
                                              (1, 1),
                                              (2, 2),
                                              (3, 4);

-- =====================================================
-- Habitats
-- =====================================================
INSERT INTO habitats (
    habitat_name,
    biome,
    location,
    min_temp_c,
    max_temp_c
) VALUES
      (
          'Crimson Rift',
          'Volcanic',
          'Sector A',
          28,
          45
      ),
      (
          'Frost Hollow',
          'Arctic',
          'Sector C',
          -15,
          2
      ),
      (
          'Emerald Canopy',
          'Jungle',
          'Sector B',
          22,
          34
      );

-- =====================================================
-- Creatures
-- =====================================================
INSERT INTO creatures (
    name,
    species,
    danger_level,
    status,
    notes,
    habitat_id
) VALUES
      (
          'Nyx',
          'Void Panther',
          'HIGH',
          'ACTIVE',
          'Extremely territorial during feeding.',
          1
      ),
      (
          'Kora',
          'Frost Serpent',
          'MEDIUM',
          'QUARANTINED',
          'Under medical observation.',
          2
      ),
      (
          'Thorn',
          'Spore Crawler',
          'LOW',
          'ACTIVE',
          'Responds well to caretaker interaction.',
          3
      ),
      (
          'Vex',
          'Ash Drifter',
          'HIGH',
          'CRITICAL',
          'Recent containment instability detected.',
          1
      ),
      (
          'Luma',
          'Sky Manta',
          'LOW',
          'REMOVED',
          'Archived after habitat transfer.',
          3
      );

-- =====================================================
-- Feeding Schedules
-- =====================================================
INSERT INTO feeding_schedules (
    creature_id,
    feeding_time,
    food_type,
    is_active,
    notes
) VALUES
      (
          1,
          '08:00',
          'Raw Plasma Feed',
          TRUE,
          'Requires two staff members present.'
      ),
      (
          2,
          '12:30',
          'Frozen Protein Mix',
          TRUE,
          'Medical staff must supervise feeding.'
      ),
      (
          3,
          '17:00',
          'Nutrient Moss',
          TRUE,
          'Low-risk feeding routine.'
      );

-- =====================================================
-- Observations
-- =====================================================
INSERT INTO observations (
    creature_id,
    user_id,
    observation_text
) VALUES
      (
          1,
          1,
          'Creature displayed elevated aggression near enclosure gate.'
      ),
      (
          2,
          2,
          'Respiration stabilized after quarantine adjustment.'
      ),
      (
          3,
          3,
          'Creature remained calm during habitat cleaning.'
      ),
      (
          4,
          1,
          'Containment field fluctuated for approximately 12 seconds.'
      );