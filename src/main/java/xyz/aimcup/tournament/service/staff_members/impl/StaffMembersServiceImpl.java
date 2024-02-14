package xyz.aimcup.tournament.service.staff_members.impl;

import feign.FeignException.FeignClientException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xyz.aimcup.generated.model.StaffMemberRequestDto;
import xyz.aimcup.tournament.data.entity.staff_member.StaffMember;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament_role.Permission;
import xyz.aimcup.tournament.data.entity.tournament_role.TournamentRole;
import xyz.aimcup.tournament.data.repository.staff_members.StaffMembersRepository;
import xyz.aimcup.tournament.feign.user.UserApiClient;
import xyz.aimcup.tournament.feign.user.models.UserResponseDTO;
import xyz.aimcup.tournament.mapper.staff_members.StaffMembersMapper;
import xyz.aimcup.tournament.service.staff_members.StaffMembersService;
import xyz.aimcup.tournament.service.tournament_roles.TournamentRolesService;

@Service
@RequiredArgsConstructor
public class StaffMembersServiceImpl implements StaffMembersService {

    private final StaffMembersRepository staffMembersRepository;
    private final StaffMembersMapper staffMembersMapper;
    private final UserApiClient userApiClient;
    private final TournamentRolesService tournamentRolesService;

    @Override
    public StaffMember getStaffMemberInTournamentById(UUID tournamentId, UUID staffMemberId) {
        return staffMembersRepository.findByIdAndTournament_Id(staffMemberId, tournamentId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Staff member with id " + staffMemberId + " not found in tournament with id " + tournamentId));
    }

    @Override
    public List<StaffMember> findAllStaffMembersInTournament(UUID tournamentId) {
        return staffMembersRepository.findAllByTournament_Id(tournamentId);
    }

    @Override
    public StaffMember createStaffMember(Tournament tournament, StaffMemberRequestDto staffMemberRequestDto) {
        try {
            UserResponseDTO userResponseDto = userApiClient.getUserByOsuId(staffMemberRequestDto.getOsuId().toString())
                .getBody();
            boolean exists = staffMembersRepository.existsByTournament_IdAndUserId(tournament.getId(),
                userResponseDto.getId());
            if (exists) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "User is already a staff member in this tournament");
            }
            List<TournamentRole> tournamentRoles = tournamentRolesService.getTournamentRolesById(tournament.getId(),
                staffMemberRequestDto.getRoles());
            List<Permission> permissions = staffMemberRequestDto.getPermissions().stream()
                .map(permission -> Permission.valueOf(permission.name()))
                .toList();
            StaffMember staffMember = staffMembersMapper.toStaffMember(staffMemberRequestDto);
            staffMember.setTournament(tournament);
            staffMember.setRoles(tournamentRoles);
            staffMember.setPermissions(permissions);
            return staffMembersRepository.save(staffMember);
        } catch (FeignClientException e) {
            throw new ResponseStatusException(e.status(), e.getMessage(), e);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

}
