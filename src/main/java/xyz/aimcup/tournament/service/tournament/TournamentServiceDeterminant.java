package xyz.aimcup.tournament.service.tournament;

import xyz.aimcup.tournament.data.entity.tournament.TournamentType;

public interface TournamentServiceDeterminant {

    SpecificTournamentService getTournamentService(TournamentType tournamentType);

}
