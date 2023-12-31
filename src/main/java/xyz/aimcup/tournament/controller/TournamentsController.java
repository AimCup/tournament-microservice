package xyz.aimcup.tournament.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.aimcup.generated.TournamentsApi;
import xyz.aimcup.generated.model.CreateTournamentRequest;
import xyz.aimcup.generated.model.SearchTournamentsDto;
import xyz.aimcup.generated.model.TournamentResponseDto;
import xyz.aimcup.tournament.mapper.tournament.TournamentMapper;
import xyz.aimcup.tournament.service.tournament.TournamentService;
import xyz.aimcup.tournament.service.tournament.TournamentServiceFacade;

@RestController
@RequiredArgsConstructor
public class TournamentsController implements TournamentsApi {

    private final TournamentServiceFacade tournamentServiceFacade;
    private final TournamentService tournamentService;
    private final TournamentMapper tournamentMapper;

    @Override
    public ResponseEntity<UUID> createTournament(CreateTournamentRequest createTournamentRequest) {
        var tournament = tournamentServiceFacade.createTournament(createTournamentRequest);
        return ResponseEntity.created(URI.create("/tournaments/" + tournament.getId()))
            .body(tournament.getId());
    }

    @Override
    public ResponseEntity<TournamentResponseDto> getTournament(UUID tournamentId) {
        var tournament = tournamentService.getTournamentById(tournamentId);
        var tournamentResponseDto = tournamentMapper.toTournamentResponseDtoFrom(tournament);
        return ResponseEntity.ok(tournamentResponseDto);
    }

    @Override
    public ResponseEntity<List<SearchTournamentsDto>> getTournaments(Integer page, Integer size) {
        var tournaments = tournamentService.getTournaments(page, size);
        var tournamentResponseDtos = tournamentMapper.toTournamentResponseDtoListFrom(tournaments);
        return ResponseEntity.ok(tournamentResponseDtos);
    }

    @Override
    public ResponseEntity<UUID> participate(UUID tournamentId) {
        throw new NotImplementedException();
    }
}
