package xyz.aimcup.tournament.service.tournament.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.aimcup.generated.model.UpdateTournamentDataRequest;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.repository.tournament.TournamentRepository;
import xyz.aimcup.tournament.mapper.tournament.TournamentMapper;
import xyz.aimcup.tournament.service.tournament.TournamentDataService;
import xyz.aimcup.tournament.service.tournament.TournamentService;

@Service
@RequiredArgsConstructor
public class TournamentDataServiceImpl implements TournamentDataService {

    private final TournamentService tournamentService;
    private final TournamentMapper tournamentMapper;
    private final TournamentRepository tournamentRepository;

    @Override
    public Tournament updateTournament(UUID id,
        UpdateTournamentDataRequest updateTournamentRequest) {
        final var tournament = tournamentService.getTournamentById(id);
        tournamentMapper.updateTournamentWith(tournament, updateTournamentRequest);
        return tournamentRepository.save(tournament);
    }
}
