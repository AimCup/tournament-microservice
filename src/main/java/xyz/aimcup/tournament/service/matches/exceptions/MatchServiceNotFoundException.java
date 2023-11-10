package xyz.aimcup.tournament.service.matches.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import xyz.aimcup.tournament.data.entity.match.MatchType;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MatchServiceNotFoundException extends RuntimeException {

    private static final String MATCH_TYPE_NOT_FOUND_FOR_SERVICE =
        "No MatchService found for type: %s";

    public MatchServiceNotFoundException(MatchType matchType) {
        super(MATCH_TYPE_NOT_FOUND_FOR_SERVICE.formatted(matchType));
    }

}
