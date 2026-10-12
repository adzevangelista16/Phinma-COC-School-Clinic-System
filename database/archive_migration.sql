-- Run this once against school_clinic_db before using the Archives screen.
-- Existing rows remain active because is_archived defaults to 0.

ALTER TABLE students
    ADD COLUMN is_archived TINYINT(1) NOT NULL DEFAULT 0,
    ADD COLUMN archived_at DATETIME NULL;

ALTER TABLE medicines
    ADD COLUMN is_archived TINYINT(1) NOT NULL DEFAULT 0,
    ADD COLUMN archived_at DATETIME NULL;

ALTER TABLE clinic_visits
    ADD COLUMN is_archived TINYINT(1) NOT NULL DEFAULT 0,
    ADD COLUMN archived_at DATETIME NULL;