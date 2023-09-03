CREATE TABLE Tournament
(
    id           UUID PRIMARY KEY,
    tournament_type VARCHAR(31),
    name         VARCHAR(255),
    abbreviation VARCHAR(255),
    created_by    UUID
);