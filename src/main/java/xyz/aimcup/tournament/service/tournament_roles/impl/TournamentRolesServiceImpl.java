package xyz.aimcup.tournament.service.tournament_roles.impl;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament_role.TournamentRole;
import xyz.aimcup.tournament.data.repository.tournament_role.TournamentRoleRepository;
import xyz.aimcup.tournament.mapper.tournament_roles.TournamentRolesMapper;
import xyz.aimcup.tournament.service.tournament.TournamentService;
import xyz.aimcup.tournament.service.tournament_roles.TournamentRolesService;

@Service
@RequiredArgsConstructor
public class TournamentRolesServiceImpl implements TournamentRolesService {

    private final TournamentRoleRepository tournamentRoleRepository;
    private final TournamentService tournamentService;
    private final TournamentRolesMapper tournamentRolesMapper;

    @Override
    public TournamentRole createTournamentRole(UUID tournamentId, TournamentRole tournamentRole) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        return this.createTournamentRole(tournament, tournamentRole);
    }

    @Override
    public TournamentRole createTournamentRole(Tournament tournament, TournamentRole tournamentRole) {
        tournamentRole.setTournament(tournament);
        return tournamentRoleRepository.save(tournamentRole);
    }

    @Override
    public List<TournamentRole> getTournamentRoles(UUID tournamentId) {
        return tournamentRoleRepository.findAllByTournament_Id(tournamentId);
    }

    @Override
    public List<TournamentRole> getTournamentRolesById(UUID tournamentId, List<UUID> tournamentRoleIds) {
        List<TournamentRole> tournamentRolesInTournament = this.getTournamentRoles(tournamentId);
        List<TournamentRole> tournamentRolesById = tournamentRolesInTournament.stream()
            .filter(tournamentRole -> tournamentRoleIds.contains(tournamentRole.getId()))
            .toList();
        if (tournamentRolesById.size() != tournamentRoleIds.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not all tournament roles were found");
        }
        return tournamentRolesById;
    }

    @Override
    public TournamentRole getTournamentRoleById(UUID tournamentId, UUID tournamentRoleId) {
        return tournamentRoleRepository.findByIdAndTournament_Id(tournamentRoleId, tournamentId)
            .orElseThrow(() -> new RuntimeException("Tournament role not found"));
    }

    @Override
    public void deleteTournamentRole(UUID tournamentId, UUID tournamentRoleId) {
        tournamentRoleRepository.deleteByIdAndTournament_Id(tournamentRoleId, tournamentId);
    }

    @Override
    public TournamentRole updateTournamentRole(UUID tournamentId, UUID id, TournamentRole tournamentRole) {
        TournamentRole tournamentRoleToUpdate = this.getTournamentRoleById(tournamentId, id);
        tournamentRolesMapper.updateTournamentRoleFromCreateRequestDto(tournamentRoleToUpdate, tournamentRole);
        return tournamentRoleRepository.save(tournamentRoleToUpdate);
    }

}
