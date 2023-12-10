package xyz.aimcup.tournament.service.tournament.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.aimcup.generated.model.CreateTournamentInfoRequest;
import xyz.aimcup.tournament.data.entity.tournament.TournamentInfo;
import xyz.aimcup.tournament.data.repository.tournament.TournamentInfoRepository;
import xyz.aimcup.tournament.service.tournament.TournamentInfoService;
import xyz.aimcup.tournament.service.tournament.TournamentService;

@Service
@RequiredArgsConstructor
public class TournamentInfoServiceImpl implements TournamentInfoService {

    private final TournamentInfoRepository tournamentInfoRepository;
    private final TournamentService tournamentService;

    @Override
    public TournamentInfo createTournamentInfo(UUID tournamentId,
        CreateTournamentInfoRequest createTournamentInfoRequest) {
        final var tournament = tournamentService.getTournamentById(tournamentId);
        var tournamentInfoToSave = TournamentInfo.builder()
            .tournament(tournament)
            .rules(createTournamentInfoRequest.getRules())
            .prizes(createTournamentInfoRequest.getPrizes())
            .createdBy(UUID.randomUUID()) //TODO: Add real user (task: AC-59)
            .build();
        tournament.setTournamentInfo(tournamentInfoToSave);
        return tournamentInfoRepository.save(tournamentInfoToSave);
    }
}
