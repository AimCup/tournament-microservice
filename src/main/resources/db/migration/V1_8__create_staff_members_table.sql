CREATE TABLE staff_members
(
    id            UUID NOT NULL PRIMARY KEY,
    joined_at     DATE NOT NULL,
    discord_id    VARCHAR(255),
    tournament_id UUID NOT NULL,
    user_id       UUID NOT NULL,
    -- matches
    -- qualificationRooms
    FOREIGN KEY (tournament_id) REFERENCES tournament (id)
);

CREATE TABLE staff_members_tournament_roles
(
    staff_member_id    UUID NOT NULL,
    tournament_role_id UUID NOT NULL,

    PRIMARY KEY (staff_member_id, tournament_role_id),
    FOREIGN KEY (staff_member_id) REFERENCES staff_members (id),
    FOREIGN KEY (tournament_role_id) REFERENCES tournament_roles (id)
);

CREATE TABLE staff_members_permissions
(
    staff_member_id UUID         NOT NULL PRIMARY KEY,
    permission      VARCHAR(255) NOT NULL,

    FOREIGN KEY (staff_member_id) REFERENCES staff_members (id)
);
