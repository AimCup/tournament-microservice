CREATE TABLE qualification_group
(
    id                 UUID NOT NULL,
    tournament_id      UUID NOT NULL,
    participants_limit INT  NOT NULL
--     foreign key (tournament_id) references tournament (id)
);
CREATE TABLE qualification_room
(
    id                 UUID NOT NULL,
    tournament_id      UUID NOT NULL,
    participants_limit INT  NOT NULL,
    referees_limit     INT,
    start_signups_time TIMESTAMP,
    end_signups_time   TIMESTAMP
--     foreign key (tournament_id) references tournament (id)
);