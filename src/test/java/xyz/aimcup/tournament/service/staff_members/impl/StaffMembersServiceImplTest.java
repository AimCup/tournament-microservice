package xyz.aimcup.tournament.service.staff_members.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import feign.FeignException.FeignClientException;
import feign.Request;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import xyz.aimcup.generated.model.StaffMemberRequestDto;
import xyz.aimcup.tournament.data.entity.staff_member.StaffMember;
import xyz.aimcup.tournament.data.entity.tournament.TeamBasedTournament;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.repository.staff_members.StaffMembersRepository;
import xyz.aimcup.tournament.feign.user.UserApiClient;
import xyz.aimcup.tournament.feign.user.models.UserResponseDTO;
import xyz.aimcup.tournament.mapper.staff_members.StaffMembersMapper;
import xyz.aimcup.tournament.service.tournament_roles.TournamentRolesService;

@ExtendWith(MockitoExtension.class)
class StaffMembersServiceImplTest {

    @InjectMocks
    private StaffMembersServiceImpl staffMembersService;

    @Mock
    private StaffMembersRepository staffMembersRepository;

    @Mock
    private StaffMembersMapper staffMembersMapper;

    @Mock
    private UserApiClient userApiClient;

    @Mock
    private TournamentRolesService tournamentRolesService;

    @Test
    @DisplayName("Should succeed when staff member exists in tournament")
    void shouldSucceedWhenStaffMemberExistsInTournament() {
        // given
        UUID tournamentId = UUID.randomUUID();
        UUID staffMemberId = UUID.randomUUID();
        StaffMember expectedStaffMember = new StaffMember();

        // when
        when(staffMembersRepository.findByIdAndTournament_Id(staffMemberId, tournamentId)).thenReturn(
            Optional.of(expectedStaffMember));

        // then
        StaffMember actualStaffMember = staffMembersService.getStaffMemberInTournamentById(tournamentId, staffMemberId);

        assertEquals(expectedStaffMember, actualStaffMember);
    }

    @Test
    @DisplayName("Should fail when staff member does not exist in tournament")
    void shouldFailWhenStaffMemberDoesNotExistInTournament() {
        // given
        UUID tournamentId = UUID.randomUUID();
        UUID staffMemberId = UUID.randomUUID();

        // when
        when(staffMembersRepository.findByIdAndTournament_Id(staffMemberId, tournamentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> staffMembersService.getStaffMemberInTournamentById(tournamentId, staffMemberId))
            .isInstanceOf(ResponseStatusException.class)
            .hasMessage("404 NOT_FOUND \"Staff member with id " + staffMemberId + " not found in tournament with id "
                + tournamentId + "\"");
    }

    @Test
    @DisplayName("Should fail when user not found")
    void shouldFailWhenUserFeignClientThrowsException() {
        // given
        StaffMemberRequestDto staffMemberRequestDto = new StaffMemberRequestDto();
        staffMemberRequestDto.setOsuId(123L);
        Request request = Request.create(Request.HttpMethod.GET, "", Map.of(), null, null, null);

        // when
        when(userApiClient.getUserByOsuId(staffMemberRequestDto.getOsuId().toString())).thenThrow(
            new FeignClientException(HttpStatus.NOT_FOUND.value(), "User not found", request, null, null));

        // then
        assertThatThrownBy(
            () -> staffMembersService.createStaffMember(new TeamBasedTournament(), staffMemberRequestDto))
            .isInstanceOf(ResponseStatusException.class)
            .hasMessage("404 NOT_FOUND \"User not found\"");
    }

    @Test
    @DisplayName("Should fail when user is already a staff member in this tournament")
    void shouldFailWhenUserIsAlreadyAStaffMemberInThisTournament() {
        // given
        UserResponseDTO userResponseDto = new UserResponseDTO();
        userResponseDto.setId(UUID.randomUUID());

        StaffMemberRequestDto staffMemberRequestDto = new StaffMemberRequestDto();
        staffMemberRequestDto.setOsuId(123L);

        Tournament tournament = new TeamBasedTournament();
        tournament.setId(UUID.randomUUID());

        // when
        when(userApiClient.getUserByOsuId(Mockito.anyString())).thenReturn(ResponseEntity.ok(userResponseDto));
        when(staffMembersRepository.existsByTournament_IdAndUserId(Mockito.any(UUID.class), Mockito.any(UUID.class)))
            .thenReturn(true);

        // then
        assertThatThrownBy(
            () -> staffMembersService.createStaffMember(tournament, staffMemberRequestDto))
            .isInstanceOf(ResponseStatusException.class)
            .hasMessage("409 CONFLICT \"User is already a staff member in this tournament\"");
    }
}