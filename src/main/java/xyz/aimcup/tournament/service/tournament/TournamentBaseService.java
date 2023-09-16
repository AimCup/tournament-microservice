package xyz.aimcup.tournament.service.tournament;

import xyz.aimcup.generated.model.CreateTournamentRequest;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

public interface TournamentBaseService {

    Tournament createTournament(CreateTournamentRequest createTournamentRequest);
}
