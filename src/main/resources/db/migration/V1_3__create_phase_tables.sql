CREATE TABLE registration_phase
(
    id            UUID PRIMARY KEY NOT NULL,
    tournament_id UUID UNIQUE      NOT NULL,
    start_time    TIMESTAMP        NOT NULL,
    end_time      TIMESTAMP        NOT NULL
);
CREATE TABLE qualification_phase
(
    id            UUID PRIMARY KEY NOT NULL,
    tournament_id UUID UNIQUE      NOT NULL,
    start_time    TIMESTAMP        NOT NULL,
    end_time      TIMESTAMP        NOT NULL
);
CREATE TABLE brackets_phase
(
    id            UUID PRIMARY KEY NOT NULL,
    tournament_id UUID             NOT NULL,
    start_time    TIMESTAMP        NOT NULL,
    end_time      TIMESTAMP        NOT NULL,
    seed_size     INT              NOT NULL
);