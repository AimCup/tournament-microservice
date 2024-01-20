package xyz.aimcup.tournament.service.tournament;

import java.util.UUID;
import xyz.aimcup.generated.model.UpdateTournamentDataRequest;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

public interface TournamentDataService {

    Tournament updateTournament(UUID id, UpdateTournamentDataRequest updateTournamentRequest);

}
