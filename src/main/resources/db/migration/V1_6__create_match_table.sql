CREATE TABLE match
(
    id                     UUID PRIMARY KEY NOT NULL,
    tournament_id          UUID             NOT NULL,
    brackets_phase_id      UUID,
    qualification_group_id UUID,
    match_type             VARCHAR(255)     NOT NULL,
    referees_limit         INTEGER          NOT NULL,
    commentators_limit     INTEGER          NOT NULL,
    streamers_limit        INTEGER          NOT NULL,
    FOREIGN KEY (tournament_id) REFERENCES tournament (id),
    FOREIGN KEY (brackets_phase_id) REFERENCES brackets_phase (id),
    FOREIGN KEY (qualification_group_id) REFERENCES qualification_group (id),
    CONSTRAINT only_brackets_or_qualification_group_not_null CHECK
        ( (brackets_phase_id IS NULL OR qualification_group_id IS NULL)
            AND NOT (brackets_phase_id IS NULL AND qualification_group_id IS NULL) )
);

CREATE TABLE participant
(
    id               UUID PRIMARY KEY NOT NULL,
    participant_type VARCHAR(20)      NOT NULL
);

CREATE TABLE match_participants
(
    match_id       UUID NOT NULL,
    participant_id UUID NOT NULL,
    FOREIGN KEY (match_id) REFERENCES match (id),
    FOREIGN KEY (participant_id) REFERENCES participant (id)
);

CREATE TABLE tournament_participants
(
    tournament_id  UUID NOT NULL,
    participant_id UUID NOT NULL,
    FOREIGN KEY (tournament_id) REFERENCES tournament (id),
    FOREIGN KEY (participant_id) REFERENCES participant (id)
);