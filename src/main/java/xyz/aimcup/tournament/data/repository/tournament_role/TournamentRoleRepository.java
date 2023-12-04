package xyz.aimcup.tournament.data.repository.tournament_role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.aimcup.tournament.data.entity.tournament_role.TournamentRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TournamentRoleRepository extends JpaRepository<TournamentRole, UUID> {
    Optional<TournamentRole> findByIdAndTournament_Id(UUID id, UUID tournamentId);
    List<TournamentRole> findAllByTournament_Id(UUID tournamentId);

    void deleteByIdAndTournament_Id(UUID id, UUID tournamentId);
}
