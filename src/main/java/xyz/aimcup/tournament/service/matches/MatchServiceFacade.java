package xyz.aimcup.tournament.service.matches;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.aimcup.generated.model.CreateMatchRequest;
import xyz.aimcup.generated.model.MatchResponseDto;
import xyz.aimcup.generated.model.SearchMatchRequest;
import xyz.aimcup.tournament.data.entity.match.Match;
import xyz.aimcup.tournament.data.entity.match.MatchType;
import xyz.aimcup.tournament.service.matches.exceptions.MatchServiceNotFoundException;
import xyz.aimcup.tournament.service.matches.impl.MatchServiceImpl;

@Component
@RequiredArgsConstructor
public class MatchServiceFacade implements MatchBaseService, MatchService {

    private final List<SpecificMatchService> matchSpecificServices;
    private final MatchServiceImpl matchBaseService;

    @Override
    public Match createMatch(CreateMatchRequest createMatchRequest) {
        final var matchService = getMatchService(
            MatchType.valueOf(createMatchRequest.getMatchType().getValue()));
        return matchService.createMatch(createMatchRequest);
    }

    @Override
    public List<MatchResponseDto> getMatches(SearchMatchRequest searchMatchRequest) {
        return matchBaseService.getMatches(searchMatchRequest);
    }

    private SpecificMatchService getMatchService(MatchType matchType) {
        return matchSpecificServices.stream()
            .filter(matchService -> matchService.isMatchingService(matchType))
            .findFirst()
            .orElseThrow(() -> new MatchServiceNotFoundException(matchType));
    }
}
