package xyz.aimcup.tournament.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;
import xyz.aimcup.generated.TournamentApi;
import xyz.aimcup.generated.model.TournamentCreateRequest;
import xyz.aimcup.generated.model.TournamentResponseDto;
import xyz.aimcup.tournament.mapper.tournament.TournamentMapper;
import xyz.aimcup.tournament.service.tournament.TournamentService;
import xyz.aimcup.tournament.service.tournament.TournamentServiceFacade;

@RestController
@RequiredArgsConstructor
public class TournamentController implements TournamentApi {

    private final TournamentServiceFacade tournamentServiceFacade;
    private final TournamentService tournamentService;
    private final TournamentMapper tournamentMapper;

    @Secured("ROLE_ADMIN")
    @Override
    public ResponseEntity<UUID> createTournament(TournamentCreateRequest tournamentCreateRequest) {
        var tournament = tournamentServiceFacade.createTournament(tournamentCreateRequest);
        return ResponseEntity.created(URI.create("/tournament/" + tournament.getId()))
            .body(tournament.getId());
    }

    @Override
    public ResponseEntity<TournamentResponseDto> getTournamentById(UUID tournamentId) {
        var tournament = tournamentService.getTournamentById(tournamentId);
        var tournamentResponseDto = tournamentMapper.toTournamentResponseDto(tournament);
        return ResponseEntity.ok(tournamentResponseDto);
    }

    @Override
    public ResponseEntity<List<TournamentResponseDto>> getTournaments() {
        var tournaments = tournamentService.getTournaments();
        var tournamentResponseDtos = tournamentMapper.toTournamentResponseDtos(tournaments);
        return ResponseEntity.ok(tournamentResponseDtos);
    }

    @Override
    public ResponseEntity<UUID> participate(UUID tournamentId) {
        throw new NotImplementedException();
    }
}
