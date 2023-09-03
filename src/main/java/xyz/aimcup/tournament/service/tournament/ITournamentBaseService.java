package xyz.aimcup.tournament.service.tournament;

import xyz.aimcup.generated.model.TournamentCreateRequest;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

public interface ITournamentBaseService {
    Tournament createTournament(TournamentCreateRequest tournamentCreateRequest);
}
