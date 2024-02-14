CREATE TABLE tournament_roles
(
    id            UUID         NOT NULL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    description   VARCHAR(255),
    tournament_id UUID         NOT NULL,

    FOREIGN KEY (tournament_id) REFERENCES tournament (id)
);

CREATE TABLE tournament_roles_permissions
(
    tournament_role_id UUID         NOT NULL PRIMARY KEY,
    permission         VARCHAR(255) NOT NULL,

    FOREIGN KEY (tournament_role_id) REFERENCES tournament_roles (id)
);