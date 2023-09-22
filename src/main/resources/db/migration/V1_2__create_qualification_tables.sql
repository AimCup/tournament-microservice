CREATE TABLE qualification_group
(
    id                     UUID PRIMARY KEY NOT NULL,
    qualification_phase_id UUID             NOT NULL,
    participants_limit     INT              NOT NULL
);
CREATE TABLE qualification_room
(
    id                     UUID PRIMARY KEY NOT NULL,
    qualification_phase_id UUID             NOT NULL,
    participants_limit     INT              NOT NULL,
    referees_limit         INT,
    start_signups_time     TIMESTAMP,
    end_signups_time       TIMESTAMP
);