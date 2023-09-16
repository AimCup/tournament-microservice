package xyz.aimcup.tournament.service.tournament.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.aimcup.generated.model.CreateTournamentRequest;
import xyz.aimcup.tournament.data.entity.tournament.InternationalTournament;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType;
import xyz.aimcup.tournament.mapper.tournament.TournamentMapper;
import xyz.aimcup.tournament.service.tournament.InternationalTournamentService;

@Service
@RequiredArgsConstructor
public class InternationalTournamentServiceImpl implements InternationalTournamentService {

    private final TournamentMapper tournamentMapper;

    @Override
    public TournamentType getTournamentType() {
        return TournamentType.INTERNATIONAL;
    }

    @Override
    public Tournament createTournament(CreateTournamentRequest createTournamentRequest) {
        InternationalTournament tournament =
            tournamentMapper.toInternationalTournamentFrom(createTournamentRequest);
        tournament.setCreatedBy(UUID.randomUUID());
        return tournament;
    }

}
