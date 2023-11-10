package xyz.aimcup.tournament.service.matches;

import java.util.List;
import xyz.aimcup.generated.model.MatchResponseDto;
import xyz.aimcup.generated.model.SearchMatchRequest;

public interface MatchBaseService {

    List<MatchResponseDto> getMatches(SearchMatchRequest searchMatchRequest);

}
