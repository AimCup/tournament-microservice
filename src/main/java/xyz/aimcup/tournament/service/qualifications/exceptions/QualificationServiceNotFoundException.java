package xyz.aimcup.tournament.service.qualifications.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import xyz.aimcup.tournament.data.entity.qualification.QualificationType;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class QualificationServiceNotFoundException extends RuntimeException {

    private static final String QUALIFICATION_TYPE_NOT_FOUND_FOR_SERVICE =
        "No QualificationService found for type: %s";

    public QualificationServiceNotFoundException(QualificationType qualificationType) {
        super(QUALIFICATION_TYPE_NOT_FOUND_FOR_SERVICE.formatted(qualificationType));
    }

}
