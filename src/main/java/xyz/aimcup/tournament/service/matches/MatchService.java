package xyz.aimcup.tournament.service.matches;

import xyz.aimcup.generated.model.CreateMatchRequest;
import xyz.aimcup.tournament.data.entity.match.Match;

public interface MatchService {

    Match createMatch(CreateMatchRequest createMatchRequest);
}
