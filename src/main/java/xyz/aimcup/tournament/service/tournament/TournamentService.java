package xyz.aimcup.tournament.service.tournament;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

@Repository
public interface TournamentService {

    List<Tournament> getTournaments(Integer page, Integer size);

    Tournament getTournamentById(UUID id);

}
