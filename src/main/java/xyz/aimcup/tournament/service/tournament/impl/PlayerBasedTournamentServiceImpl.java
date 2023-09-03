package xyz.aimcup.tournament.service.tournament.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.aimcup.generated.model.TournamentCreateRequest;
import xyz.aimcup.tournament.data.entity.tournament.PlayerBasedTournament;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType;
import xyz.aimcup.tournament.data.repository.tournament.TournamentRepository;
import xyz.aimcup.tournament.service.tournament.IPlayerBasedTournamentService;

@Service
@RequiredArgsConstructor
public class PlayerBasedTournamentServiceImpl implements IPlayerBasedTournamentService {
    private final TournamentRepository tournamentRepository;
    @Override
    public TournamentType getTournamentType() {
        return TournamentType.PLAYER_VS;
    }

    @Override
    public Tournament createTournament(TournamentCreateRequest tournamentCreateRequest) {
        PlayerBasedTournament tournament = PlayerBasedTournament.builder()
            .name(tournamentCreateRequest.getName())
            .abbreviation(tournamentCreateRequest.getAbbreviation())
            .createdBy(UUID.randomUUID())
            .build();
        return tournamentRepository.save(tournament);
    }
}
