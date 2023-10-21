package xyz.aimcup.tournament.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import xyz.aimcup.generated.MatchesApi;
import xyz.aimcup.generated.model.CreateMatchRequest;
import xyz.aimcup.generated.model.MatchResponseDto;
import xyz.aimcup.generated.model.SearchMatchRequest;
import xyz.aimcup.tournament.service.matches.MatchServiceFacade;

@Controller
@RequiredArgsConstructor
public class MatchesController implements MatchesApi {

    private final MatchServiceFacade matchServiceFacade;


    @Override
    public ResponseEntity<UUID> createMatch(CreateMatchRequest createMatchRequest) {
        final var match = matchServiceFacade.createMatch(createMatchRequest);
        return ResponseEntity.created(URI.create("/matches/" + match.getId()))
            .body(match.getId());
    }

    @Override
    public ResponseEntity<List<MatchResponseDto>> getMatches(
        SearchMatchRequest searchMatchRequest) {
        final var matchesFound = matchServiceFacade.getMatches(searchMatchRequest);
        return ResponseEntity.ok(matchesFound);
    }

}
