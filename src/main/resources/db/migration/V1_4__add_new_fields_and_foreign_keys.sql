ALTER TABLE tournament
    ADD COLUMN qualification_type VARCHAR(31) NOT NULL;
ALTER TABLE tournament
    ADD FOREIGN KEY (id) REFERENCES tournament_data (tournament_id);
ALTER TABLE qualification_group
    ADD FOREIGN KEY (qualification_phase_id) REFERENCES qualification_phase (id);
ALTER TABLE qualification_room
    ADD FOREIGN KEY (qualification_phase_id) REFERENCES qualification_phase (id);
ALTER TABLE qualification_phase
    ADD FOREIGN KEY (tournament_id) REFERENCES tournament (id);
ALTER TABLE registration_phase
    ADD FOREIGN KEY (tournament_id) REFERENCES tournament (id);
ALTER TABLE brackets_phase
    ADD FOREIGN KEY (tournament_id) REFERENCES tournament (id);