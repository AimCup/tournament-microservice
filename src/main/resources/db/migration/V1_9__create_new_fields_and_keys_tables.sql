ALTER TABLE tournament_data
    ADD COLUMN team_size INT NOT NULL;
ALTER TABLE tournament_data
    ADD COLUMN max_team_size INT NOT NULL;
ALTER TABLE tournament_info
    ADD COLUMN created_by UUID NOT NULL;
ALTER TABLE tournament_info
    RENAME COLUMN updated_at TO created_at;
ALTER TABLE tournament_info
    ADD COLUMN version INT NOT NULL;
ALTER TABLE tournament_info
    RENAME COLUMN id TO tournament_id;
ALTER TABLE tournament_info
    ADD PRIMARY KEY (tournament_id);
ALTER TABLE qualification_room
    ADD COLUMN qualification_room_type VARCHAR(31) NOT NULL;

ALTER TABLE tournament_data
    ADD FOREIGN KEY (tournament_id) REFERENCES tournament (id);
ALTER TABLE tournament_info
    ADD FOREIGN KEY (tournament_id) REFERENCES tournament (id);

CREATE OR REPLACE FUNCTION update_tournament_info_created_at_column()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.created_at = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_tournament_info
    BEFORE UPDATE
    ON
        tournament_info
    FOR EACH ROW
EXECUTE PROCEDURE update_tournament_info_created_at_column();