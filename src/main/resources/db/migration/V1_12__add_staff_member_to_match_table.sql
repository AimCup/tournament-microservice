ALTER TABLE match
    ADD COLUMN referee_id UUID REFERENCES staff_members (id);
ALTER TABLE match
    ADD COLUMN streamer_id UUID REFERENCES staff_members (id);
ALTER TABLE match
    ADD COLUMN commentator_id UUID REFERENCES staff_members (id);

CREATE TABLE match_commentators
(
    staff_member_id UUID REFERENCES staff_members (id),
    match_id UUID REFERENCES match (id),
    PRIMARY KEY (staff_member_id, match_id),
    FOREIGN KEY (staff_member_id) REFERENCES staff_members (id),
    FOREIGN KEY (match_id) REFERENCES match (id)
);
