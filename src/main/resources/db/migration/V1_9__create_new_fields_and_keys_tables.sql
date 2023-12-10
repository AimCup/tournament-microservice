ALTER TABLE tournament_data
    ADD COLUMN team_size INT NOT NULL;
ALTER TABLE tournament_data
    ADD COLUMN max_team_size INT NOT NULL;
ALTER TABLE tournament_info
    ADD COLUMN created_by UUID NOT NULL;
ALTER TABLE tournament_info
    RENAME COLUMN updated_at TO created_at;
ALTER TABLE tournament_info
    ADD COLUMN version INT;
ALTER TABLE tournament_info
    ADD COLUMN tournament_id UUID NOT NULL;
ALTER TABLE tournament
    ADD COLUMN tournament_info_id UUID;
ALTER TABLE qualification_room
    ADD COLUMN qualification_room_type VARCHAR(31) NOT NULL;

ALTER TABLE tournament_data
    ADD FOREIGN KEY (tournament_id) REFERENCES tournament (id);
ALTER TABLE tournament_info
    ADD FOREIGN KEY (tournament_id) REFERENCES tournament (id);

CREATE OR REPLACE FUNCTION update_tournament_info()
    RETURNS TRIGGER AS
$$
DECLARE
    old_version INT := (SELECT max(tournament_info.version) FROM tournament_info
        where tournament_info.tournament_id = NEW.tournament_id);
BEGIN
    NEW.created_at = now();
    IF (old_version is null) THEN
        NEW.version = 1;
    ELSE
        NEW.version = old_version + 1;
    END IF;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_tournament_info
    BEFORE INSERT OR UPDATE
    ON
        tournament_info
    FOR EACH ROW
EXECUTE PROCEDURE update_tournament_info();