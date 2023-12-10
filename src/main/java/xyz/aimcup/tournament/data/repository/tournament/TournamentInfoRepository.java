package xyz.aimcup.tournament.data.repository.tournament;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.aimcup.tournament.data.entity.tournament.TournamentInfo;

public interface TournamentInfoRepository extends JpaRepository<TournamentInfo, UUID> {

}
