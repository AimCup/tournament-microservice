package xyz.aimcup.tournament.service.tournament;

import java.util.UUID;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType;

public interface ITournamentServiceDeterminant {
    ITournamentService getTournamentService(UUID tournamentId);
    ITournamentService getTournamentService(Tournament tournament);
    ITournamentService getTournamentService(TournamentType tournamentType);

}
