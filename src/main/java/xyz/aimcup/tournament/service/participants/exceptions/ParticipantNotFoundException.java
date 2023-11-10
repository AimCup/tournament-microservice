package xyz.aimcup.tournament.service.participants.exceptions;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ParticipantNotFoundException extends RuntimeException {

    private static final String PARTICIPANT_NOT_FOUND =
        "No participant found with given id: %s";

    public ParticipantNotFoundException(UUID participantId) {
        super(PARTICIPANT_NOT_FOUND.formatted(participantId));
    }
}
