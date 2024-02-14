package xyz.aimcup.tournament.service.tournament_roles;

import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament_role.TournamentRole;

import java.util.List;
import java.util.UUID;

public interface TournamentRolesService {
    TournamentRole createTournamentRole(UUID tournamentId, TournamentRole tournamentRole);
    TournamentRole createTournamentRole(Tournament tournament, TournamentRole tournamentRole);
    List<TournamentRole> getTournamentRoles(UUID tournamentId);
    List<TournamentRole> getTournamentRolesById(UUID tournamentId, List<UUID> tournamentRoleIds);
    TournamentRole getTournamentRoleById(UUID tournamentId, UUID tournamentRoleId);
    void deleteTournamentRole(UUID tournamentId, UUID tournamentRoleId);
    TournamentRole updateTournamentRole(UUID tournamentId, UUID id, TournamentRole tournamentRole);
}
