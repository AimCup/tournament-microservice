package xyz.aimcup.tournament.service.phases.exceptions;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import xyz.aimcup.tournament.data.entity.phase.TournamentPhaseType;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PhaseNotFoundException extends RuntimeException {

    private static final String PHASE_NOT_FOUND =
        "No %s phase found with given id: %s";

    public PhaseNotFoundException(TournamentPhaseType tournamentPhaseType, UUID qualificationGroupId) {
        super(PHASE_NOT_FOUND.formatted(tournamentPhaseType, qualificationGroupId));
    }

}
