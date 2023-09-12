package xyz.aimcup.tournament.service.tournament;

import xyz.aimcup.generated.model.TournamentCreateRequest;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

public interface TournamentBaseService {
    Tournament createTournament(TournamentCreateRequest tournamentCreateRequest);
//    Tournament getTournamentById(UUID tournamentId);
//    List<Tournament> getTournaments();
}
