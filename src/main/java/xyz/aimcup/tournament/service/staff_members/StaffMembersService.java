package xyz.aimcup.tournament.service.staff_members;

import xyz.aimcup.tournament.data.entity.staff_member.StaffMember;

import java.util.List;
import java.util.UUID;

public interface StaffMembersService {
    List<StaffMember> findAllStaffMembersInTournament(UUID tournamentId);
}
