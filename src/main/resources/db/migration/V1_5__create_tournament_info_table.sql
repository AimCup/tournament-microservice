CREATE TABLE tournament_info
(
    id         UUID PRIMARY KEY NOT NULL,
    rules      TEXT,
    prizes     TEXT,
    edited_by  UUID             NOT NULL,
    updated_at TIMESTAMP default now()
);

ALTER TABLE tournament_data
    ADD FOREIGN KEY (tournament_info_id) REFERENCES tournament_info (id);

CREATE OR REPLACE FUNCTION update_tournament_info_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = now();
   RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_tournament_info
    BEFORE UPDATE
    ON
        tournament_info
    FOR EACH ROW
EXECUTE PROCEDURE update_tournament_info_updated_at_column();