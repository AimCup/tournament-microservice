package xyz.aimcup.tournament.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.aimcup.generated.TournamentRolesApi;
import xyz.aimcup.generated.model.CreateTournamentRoleRequestDto;
import xyz.aimcup.generated.model.TournamentRoleResponseDto;
import xyz.aimcup.tournament.data.entity.tournament_role.Permission;
import xyz.aimcup.tournament.data.entity.tournament_role.TournamentRole;
import xyz.aimcup.tournament.mapper.tournament_roles.TournamentRolesMapper;
import xyz.aimcup.tournament.service.tournament_roles.TournamentRolesService;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class TournamentRolesController implements TournamentRolesApi {
    private final TournamentRolesService tournamentRolesService;
    private final TournamentRolesMapper tournamentRolesMapper;

    @Override
    public ResponseEntity<UUID> createTournamentRole(UUID tournamentId, CreateTournamentRoleRequestDto createTournamentRoleRequestDto) {
        TournamentRole tournamentRole = tournamentRolesMapper.mapTournamentRoleFromCreateRequestDto(createTournamentRoleRequestDto);
        TournamentRole newTournamentRole = tournamentRolesService.createTournamentRole(tournamentId, tournamentRole);
        return ResponseEntity.created(URI.create("/tournaments/" + tournamentId + "/roles/" + newTournamentRole.getId()))
                .body(newTournamentRole.getId());
    }

    @Override
    public ResponseEntity<Void> deleteTournamentRole(UUID tournamentId, UUID tournamentRoleId) {
        tournamentRolesService.deleteTournamentRole(tournamentId, tournamentRoleId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<String>> getPermissions() {
        List<String> permissionsAsString = Stream.of(Permission.values())
                .map(Permission::name)
                .toList();
        return ResponseEntity.ok(permissionsAsString);
    }

    @Override
    public ResponseEntity<TournamentRoleResponseDto> getTournamentRole(UUID tournamentId, UUID tournamentRoleId) {
        TournamentRole tournamentRole = tournamentRolesService.getTournamentRoleById(tournamentId, tournamentRoleId);
        TournamentRoleResponseDto tournamentRoleResponseDto = tournamentRolesMapper.mapTournamentRoleToResponseDto(tournamentRole);
        return ResponseEntity.ok(tournamentRoleResponseDto);
    }

    @Override
    public ResponseEntity<List<TournamentRoleResponseDto>> getTournamentRoles(UUID tournamentId) {
        List<TournamentRole> tournamentRoles = tournamentRolesService.getTournamentRoles(tournamentId);
        List<TournamentRoleResponseDto> tournamentRoleResponseDtos = tournamentRolesMapper.mapTournamentRolesToResponseDtos(tournamentRoles);
        return ResponseEntity.ok(tournamentRoleResponseDtos);
    }

    @Override
    public ResponseEntity<TournamentRoleResponseDto> updateTournamentRole(UUID tournamentId, UUID tournamentRoleId, CreateTournamentRoleRequestDto createTournamentRoleRequestDto) {
        TournamentRole tournamentRole = tournamentRolesMapper.mapTournamentRoleFromCreateRequestDto(createTournamentRoleRequestDto);
        TournamentRole updatedTournamentRole = tournamentRolesService.updateTournamentRole(tournamentId, tournamentRoleId, tournamentRole);
        TournamentRoleResponseDto tournamentRoleResponseDto = tournamentRolesMapper.mapTournamentRoleToResponseDto(updatedTournamentRole);
        return ResponseEntity.ok(tournamentRoleResponseDto);
    }

}
