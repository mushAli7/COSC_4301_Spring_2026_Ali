-- =====================================================
-- Neon Ark Creature Management System
-- V1 - Core Tables
--
-- This migration creates the main foundation for the
-- capstone backend: users, roles, habitats, and creatures.
-- Flyway owns schema creation so Hibernate only validates it.
-- =====================================================

-- =====================================================
-- Roles define access categories for system users.
-- These values support the admin user listing route and
-- allow user permissions to stay consistent.
-- =====================================================
CREATE TABLE roles (
                       role_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       role_name VARCHAR(50) NOT NULL UNIQUE
);

-- =====================================================
-- Users represent staff members who can access the system.
-- The admin route displays user contact information and role.
-- =====================================================
CREATE TABLE users (
                       user_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       full_name VARCHAR(120) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       phone VARCHAR(30),
                       username VARCHAR(80) NOT NULL UNIQUE,
                       is_active BOOLEAN NOT NULL DEFAULT TRUE,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- Join table between users and roles.
-- This allows one user to have one or more roles without
-- duplicating role text in the users table.
-- =====================================================
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,

                            CONSTRAINT pk_user_roles PRIMARY KEY (user_id, role_id),

                            CONSTRAINT fk_user_roles_user
                                FOREIGN KEY (user_id)
                                    REFERENCES users(user_id),

                            CONSTRAINT fk_user_roles_role
                                FOREIGN KEY (role_id)
                                    REFERENCES roles(role_id)
);

-- =====================================================
-- Habitats describe where creatures are housed.
-- Creature list and detail routes must return habitat names,
-- so this table supports required joins for CLI output.
-- =====================================================
CREATE TABLE habitats (
                          habitat_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          habitat_name VARCHAR(120) NOT NULL UNIQUE,
                          biome VARCHAR(80) NOT NULL,
                          location VARCHAR(120) NOT NULL,
                          min_temp_c INT NOT NULL,
                          max_temp_c INT NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          CONSTRAINT chk_habitat_temp_range
                              CHECK (max_temp_c >= min_temp_c)
);

-- =====================================================
-- Creatures are the main operational records in the system.
-- Status includes REMOVED so delete actions preserve history
-- instead of physically deleting rows.
-- =====================================================
CREATE TABLE creatures (
                           creature_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           name VARCHAR(120) NOT NULL,
                           species VARCHAR(120) NOT NULL,
                           danger_level VARCHAR(30) NOT NULL,
                           status VARCHAR(30) NOT NULL,
                           notes TEXT,
                           habitat_id BIGINT NOT NULL,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                           CONSTRAINT fk_creatures_habitat
                               FOREIGN KEY (habitat_id)
                                   REFERENCES habitats(habitat_id),

    -- Prevents duplicate creature names inside the same habitat.
    -- This supports the required 409 conflict behavior.
                           CONSTRAINT uq_creature_name_per_habitat
                               UNIQUE (habitat_id, name),

                           CONSTRAINT chk_creature_danger_level
                               CHECK (danger_level IN ('LOW', 'MEDIUM', 'HIGH')),

                           CONSTRAINT chk_creature_status
                               CHECK (status IN ('ACTIVE', 'QUARANTINED', 'CRITICAL', 'REMOVED'))
);