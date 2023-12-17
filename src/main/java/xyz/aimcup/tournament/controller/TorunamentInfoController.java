package xyz.aimcup.tournament.controller;

import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.aimcup.generated.TournamentInfoApi;
import xyz.aimcup.generated.model.CreateTournamentInfoRequest;
import xyz.aimcup.tournament.service.tournament.TournamentInfoService;

@RestController
@RequiredArgsConstructor
public class TorunamentInfoController implements TournamentInfoApi {

    private final TournamentInfoService tournamentInfoService;

    @Override
    public ResponseEntity<UUID> createTournamentInfo(UUID tournamentId,
        CreateTournamentInfoRequest createTournamentInfoRequest) {
        final var createdTournamentInfo = tournamentInfoService
            .createTournamentInfo(tournamentId, createTournamentInfoRequest);
        return ResponseEntity.created(URI.create("/tournaments/" + tournamentId))
            .body(createdTournamentInfo.getId());
    }
}
