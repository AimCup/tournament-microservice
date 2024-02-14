package xyz.aimcup.tournament.service.staff_members;

import xyz.aimcup.generated.model.StaffMemberRequestDto;
import xyz.aimcup.tournament.data.entity.staff_member.StaffMember;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

import java.util.List;
import java.util.UUID;

public interface StaffMembersService {

    StaffMember getStaffMemberInTournamentById(UUID tournamentId, UUID staffMemberId);
    List<StaffMember> findAllStaffMembersInTournament(UUID tournamentId);
    StaffMember createStaffMember(Tournament tournament, StaffMemberRequestDto staffMemberRequestDto);
}
