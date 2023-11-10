package xyz.aimcup.tournament.service.qualifications.exceptions;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class QualificationGroupNotFoundException extends RuntimeException {

    private static final String QUALIFICATION_GROUP_NOT_FOUND =
        "No Qualification group found for id: %s";

    public QualificationGroupNotFoundException(UUID qualificationGroupId) {
        super(QUALIFICATION_GROUP_NOT_FOUND.formatted(qualificationGroupId));
    }

}
