ALTER TABLE tournament
    ADD COLUMN signing_enabled BOOLEAN NOT NULL DEFAULT false;
ALTER TABLE tournament
    ADD COLUMN qualification_type VARCHAR(31) NOT NULL;