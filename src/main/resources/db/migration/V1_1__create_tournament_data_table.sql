CREATE TABLE tournament_data
(
    tournament_id                             UUID PRIMARY KEY NOT NULL,
    participants_limit                        INT              NOT NULL,
    referees_limit                            INT              NOT NULL,
    commentators_limit                        INT              NOT NULL,
    streamers_limit                           INT              NOT NULL,
    players_per_beatmap_limit                 INT              NOT NULL,
    minimum_rank_limit                        INT,
    maximum_rank_limit                        INT,
    participants_per_qualification_spot_limit INT              NOT NULL,
    tournament_rules_id                       UUID
);