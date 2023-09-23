package xyz.aimcup.tournament.service.qualifications.exceptions;

import xyz.aimcup.tournament.data.entity.qualification.QualificationType;

public class QualificationServiceNotFoundException extends RuntimeException {

    private static final String QUALIFICATION_TYPE_NOT_FOUND_FOR_SERVICE =
        "No QualificationService found for type: %s";

    public QualificationServiceNotFoundException(QualificationType qualificationType) {
        super(QUALIFICATION_TYPE_NOT_FOUND_FOR_SERVICE.formatted(qualificationType));
    }

}
