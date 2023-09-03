package xyz.aimcup.tournament.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.aimcup.generated.TournamentApi;
import xyz.aimcup.generated.model.TournamentCreateRequest;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.service.tournament.TournamentServiceFacade;

@RestController
@RequiredArgsConstructor
public class TournamentController implements TournamentApi {

    private final TournamentServiceFacade tournamentService;

    @Override
    public ResponseEntity<String> tournamentCreate(
        TournamentCreateRequest tournamentCreateRequest) {
        Tournament tournament = tournamentService.createTournament(tournamentCreateRequest);
        return ResponseEntity.created(URI.create("/tournament/" + tournament.getId()))
            .body(tournament.getId().toString());
    }
}
