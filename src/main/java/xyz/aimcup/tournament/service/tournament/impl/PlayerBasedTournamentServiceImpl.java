package xyz.aimcup.tournament.service.tournament.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Service;
import xyz.aimcup.generated.model.TournamentCreateRequest;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType;
import xyz.aimcup.tournament.service.tournament.PlayerBasedTournamentService;

@Service
@RequiredArgsConstructor
public class PlayerBasedTournamentServiceImpl implements PlayerBasedTournamentService {
    @Override
    public TournamentType getTournamentType() {
        return TournamentType.PLAYER_VS;
    }

    @Override
    public Tournament createTournament(TournamentCreateRequest tournamentCreateRequest) {
        throw new NotImplementedException();
    }
}
