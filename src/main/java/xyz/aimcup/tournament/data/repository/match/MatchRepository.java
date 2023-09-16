package xyz.aimcup.tournament.data.repository.match;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.aimcup.tournament.data.entity.match.Match;

public interface MatchRepository extends JpaRepository<Match, UUID> {

}
