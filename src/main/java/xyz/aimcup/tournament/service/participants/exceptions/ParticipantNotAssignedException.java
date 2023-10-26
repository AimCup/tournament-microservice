package xyz.aimcup.tournament.service.participants.exceptions;

import java.util.SortedSet;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ParticipantNotAssignedException extends RuntimeException {

    private static final String PARTICIPANT_NOT_ASSIGNED =
        "Some participants were not found for %s: %s, found: %s";

    public ParticipantNotAssignedException(
        String assigningSourceName, SortedSet<UUID> participantsIds,
        SortedSet<UUID> participantsIdsFound) {
        super(PARTICIPANT_NOT_ASSIGNED.formatted(assigningSourceName, participantsIds,
            participantsIdsFound));
    }

}
