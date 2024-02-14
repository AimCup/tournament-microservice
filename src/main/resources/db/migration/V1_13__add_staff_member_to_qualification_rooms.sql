ALTER TABLE qualification_room
    ADD COLUMN staff_member_id UUID REFERENCES staff_members (id);