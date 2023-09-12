package xyz.aimcup.tournament.data.repository.tournament;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, UUID> {

}
