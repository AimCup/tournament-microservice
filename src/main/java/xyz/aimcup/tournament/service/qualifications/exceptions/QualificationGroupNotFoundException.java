package xyz.aimcup.tournament.service.qualifications.exceptions;

import java.util.UUID;

public class QualificationGroupNotFoundException extends RuntimeException {

    private static final String QUALIFICATION_GROUP_NOT_FOUND =
        "No Qualification group found for id: %s";

    public QualificationGroupNotFoundException(UUID qualificationGroupId) {
        super(QUALIFICATION_GROUP_NOT_FOUND.formatted(qualificationGroupId));
    }

}
