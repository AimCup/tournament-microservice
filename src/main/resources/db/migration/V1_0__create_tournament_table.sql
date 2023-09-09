CREATE TABLE Tournament
(
    id              UUID PRIMARY KEY NOT NULL,
    tournament_type VARCHAR(31)      NOT NULL,
    name            VARCHAR(255)     NOT NULL,
    abbreviation    VARCHAR(255)     NOT NULL,
    created_by      UUID             NOT NULL
);