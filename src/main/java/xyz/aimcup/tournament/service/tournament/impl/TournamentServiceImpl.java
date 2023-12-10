package xyz.aimcup.tournament.service.tournament.impl;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import xyz.aimcup.generated.model.UpdateTournamentRequest;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.repository.tournament.TournamentRepository;
import xyz.aimcup.tournament.mapper.tournament.TournamentMapper;
import xyz.aimcup.tournament.service.tournament.TournamentService;

@Repository
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {
    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    @Override
    public List<Tournament> getTournaments(Integer page, Integer size) {
        final var pageToRetrieve = PageRequest.of(page, size,
            Sort.by(Direction.DESC,"registrationPhase.startTime"));
        return tournamentRepository.findAll(pageToRetrieve).getContent();
    }

    @Override
    public Tournament getTournamentById(UUID id) {
        return tournamentRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Tournament with id " + id + " not found"));
    }

    @Override
    public Tournament updateTournament(UUID id, UpdateTournamentRequest updateTournamentRequest) {
        final var tournament = getTournamentById(id);
        tournamentMapper.updateTournament(tournament, updateTournamentRequest);
        return tournamentRepository.save(tournament);
    }
}
