package xyz.aimcup.tournament.service.tournament;

import java.util.UUID;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType;

public interface TournamentServiceDeterminant {
    SpecificTournamentService getTournamentService(UUID tournamentId);
    SpecificTournamentService getTournamentService(Tournament tournament);
    SpecificTournamentService getTournamentService(TournamentType tournamentType);

}
