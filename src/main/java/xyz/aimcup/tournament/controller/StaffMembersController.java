package xyz.aimcup.tournament.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.aimcup.generated.StaffMembersApi;
import xyz.aimcup.generated.model.StaffMemberRequestDto;
import xyz.aimcup.generated.model.StaffMemberResponseDto;
import xyz.aimcup.tournament.data.entity.staff_member.StaffMember;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.mapper.staff_members.StaffMembersMapper;
import xyz.aimcup.tournament.service.staff_members.StaffMembersService;
import xyz.aimcup.tournament.service.tournament.TournamentService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StaffMembersController implements StaffMembersApi {

    private final StaffMembersService staffMembersService;
    private final TournamentService tournamentService;
    private final StaffMembersMapper staffMembersMapper;

    @Override
    public ResponseEntity<StaffMemberResponseDto> createStaffMember(UUID tournamentId,
        StaffMemberRequestDto staffMemberRequestDto) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        StaffMember staffMember = staffMembersService.createStaffMember(tournament, staffMemberRequestDto);
        StaffMemberResponseDto staffMemberResponse = staffMembersMapper.toStaffMemberResponse(staffMember);
        return ResponseEntity.created(URI.create("/tournaments/" + tournamentId + "/staff/" + staffMember.getId()))
            .body(staffMemberResponse);
    }

    @Override
    public ResponseEntity<StaffMemberResponseDto> getStaffMemberById(UUID tournamentId, UUID staffMemberId) {
        StaffMember staffMember = staffMembersService.getStaffMemberInTournamentById(tournamentId, staffMemberId);
        StaffMemberResponseDto staffMemberResponse = staffMembersMapper.toStaffMemberResponse(staffMember);
        return ResponseEntity.ok(staffMemberResponse);
    }

    @Override
    public ResponseEntity<List<StaffMemberResponseDto>> getStaffMembers(UUID tournamentId) {
        List<StaffMember> staffMembersInTournament = staffMembersService.findAllStaffMembersInTournament(tournamentId);
        List<StaffMemberResponseDto> staffMemberResponseDtos = staffMembersMapper.toStaffMemberResponse(
            staffMembersInTournament);
        return ResponseEntity.ok(staffMemberResponseDtos);
    }
}
