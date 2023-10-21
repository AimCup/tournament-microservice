package xyz.aimcup.tournament.service.matches;

import xyz.aimcup.tournament.data.entity.match.MatchType;

public interface SpecificMatchService extends MatchService {
    boolean isMatchingService(MatchType matchType);

}
