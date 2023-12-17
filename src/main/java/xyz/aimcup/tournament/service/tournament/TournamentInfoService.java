package xyz.aimcup.tournament.service.tournament;

import java.util.UUID;
import xyz.aimcup.generated.model.CreateTournamentInfoRequest;
import xyz.aimcup.tournament.data.entity.tournament.TournamentInfo;

public interface TournamentInfoService {

    TournamentInfo createTournamentInfo(UUID tournamentId,
        CreateTournamentInfoRequest createTournamentInfoRequest);

}
