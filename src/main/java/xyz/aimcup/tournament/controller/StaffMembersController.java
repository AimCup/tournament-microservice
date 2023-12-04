package xyz.aimcup.tournament.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.aimcup.generated.StaffMembersApi;
import xyz.aimcup.generated.model.StaffMemberResponseDto;
import xyz.aimcup.tournament.data.entity.staff_member.StaffMember;
import xyz.aimcup.tournament.mapper.staff_members.StaffMembersMapper;
import xyz.aimcup.tournament.service.staff_members.StaffMembersService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StaffMembersController implements StaffMembersApi {
    private final StaffMembersService staffMembersService;
    private final StaffMembersMapper staffMembersMapper;
    @Override
    public ResponseEntity<List<StaffMemberResponseDto>> getStaffMembers(UUID tournamentId) {
        List<StaffMember> staffMembersInTournament = staffMembersService.findAllStaffMembersInTournament(tournamentId);
        List<StaffMemberResponseDto> staffMemberResponseDtos = staffMembersMapper.toStaffMemberResponse(staffMembersInTournament);
        return ResponseEntity.ok(staffMemberResponseDtos);
    }
}
