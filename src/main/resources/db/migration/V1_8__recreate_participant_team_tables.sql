ALTER TABLE participant
    ADD COLUMN user_id UUID NOT NULL;

ALTER TABLE participant
    ADD COLUMN team_id UUID NOT NULL;

ALTER TABLE participant
    ADD COLUMN qualification_room_id UUID NOT NULL;

ALTER TABLE participant
    ADD FOREIGN KEY (qualification_room_id) REFERENCES qualification_room (id);

ALTER TABLE tournament_participants
    ADD UNIQUE (participant_id);

CREATE TABLE team
(
    id                         UUID PRIMARY KEY NOT NULL,
    name                       VARCHAR(30)      NOT NULL,
    logo_url                   TEXT,
    seed                       INT,
    average_performance_points NUMERIC          NOT NULL,
    captain                    UUID             NOT NULL,
    qualification_room_id      UUID             NOT NULL,
    FOREIGN KEY (captain) REFERENCES participant (id),
    FOREIGN KEY (qualification_room_id) REFERENCES qualification_room (id)
);

CREATE TABLE tournament_teams
(
    tournament_id UUID NOT NULL,
    team_id       UUID NOT NULL UNIQUE,
    FOREIGN KEY (tournament_id) REFERENCES tournament (id),
    FOREIGN KEY (team_id) REFERENCES team (id)
);

CREATE TABLE match_teams
(
    match_id       UUID NOT NULL,
    team_id UUID NOT NULL,
    FOREIGN KEY (match_id) REFERENCES match (id),
    FOREIGN KEY (team_id) REFERENCES team (id)
);

ALTER TABLE participant
    ADD FOREIGN KEY (team_id) REFERENCES team (id);