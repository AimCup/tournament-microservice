package xyz.aimcup.tournament.service.tournament.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.aimcup.generated.model.CreateTournamentRequest;
import xyz.aimcup.tournament.data.entity.tournament.PlayerBasedTournament;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType;
import xyz.aimcup.tournament.mapper.tournament.TournamentMapper;
import xyz.aimcup.tournament.service.tournament.PlayerBasedTournamentService;

@Service
@RequiredArgsConstructor
public class PlayerBasedTournamentServiceImpl implements PlayerBasedTournamentService {

    private final TournamentMapper tournamentMapper;

    @Override
    public TournamentType getTournamentType() {
        return TournamentType.PLAYER_VS;
    }

    @Override
    public Tournament createTournament(CreateTournamentRequest createTournamentRequest) {
        PlayerBasedTournament tournament =
            tournamentMapper.toPlayerBasedTournamentFrom(createTournamentRequest);
        tournament.setCreatedBy(UUID.randomUUID());
        return tournament;
    }
}
