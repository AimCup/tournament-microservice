package xyz.aimcup.tournament.service.tournament.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Service;
import xyz.aimcup.generated.model.TournamentCreateRequest;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType;
import xyz.aimcup.tournament.service.tournament.InternationalTournamentService;

@Service
@RequiredArgsConstructor
public class InternationalTournamentServiceImpl implements InternationalTournamentService {

    @Override
    public TournamentType getTournamentType() {
        return TournamentType.INTERNATIONAL;
    }

    @Override
    public Tournament createTournament(TournamentCreateRequest tournamentCreateRequest) {
        throw new NotImplementedException();
    }
}
