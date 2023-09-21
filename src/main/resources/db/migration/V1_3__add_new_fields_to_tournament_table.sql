ALTER TABLE tournament
    ADD COLUMN signing_enabled BOOLEAN NOT NULL DEFAULT false;
ALTER TABLE tournament
    ADD COLUMN qualification_type VARCHAR(31) NOT NULL;
ALTER TABLE tournament
    ADD FOREIGN KEY (id) REFERENCES tournament_data (tournament_id);
ALTER TABLE qualification_group
    ADD FOREIGN KEY (tournament_id) REFERENCES tournament (id);
ALTER TABLE qualification_room
    ADD FOREIGN KEY (tournament_id) REFERENCES tournament (id);