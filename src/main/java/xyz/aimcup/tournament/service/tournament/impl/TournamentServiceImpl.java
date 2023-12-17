package xyz.aimcup.tournament.service.tournament.impl;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.repository.tournament.TournamentRepository;
import xyz.aimcup.tournament.service.tournament.TournamentService;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;

    @Override
    public List<Tournament> getTournaments(Integer page, Integer size) {
        final var pageToRetrieve = PageRequest.of(page, size,
            Sort.by(Direction.DESC, "registrationPhase.startTime"));
        return tournamentRepository.findAll(pageToRetrieve).getContent();
    }

    @Override
    public Tournament getTournamentById(UUID id) {
        return tournamentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Tournament with id " + id + " not found"));
    }
}
