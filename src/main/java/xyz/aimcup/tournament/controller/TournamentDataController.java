package xyz.aimcup.tournament.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.aimcup.generated.TournamentDataApi;
import xyz.aimcup.generated.model.UpdateTournamentDataRequest;
import xyz.aimcup.tournament.service.tournament.TournamentDataService;

@RestController
@RequiredArgsConstructor
public class TournamentDataController implements TournamentDataApi {

    private final TournamentDataService tournamentDataService;

    @Override
    public ResponseEntity<UUID> updateTournamentInfo(UUID tournamentId,
        UpdateTournamentDataRequest updateTournamentDataRequest) {
        final var updatedTorunament = tournamentDataService
            .updateTournament(tournamentId, updateTournamentDataRequest);
        return ResponseEntity.ok(updatedTorunament.getId());
    }
}
