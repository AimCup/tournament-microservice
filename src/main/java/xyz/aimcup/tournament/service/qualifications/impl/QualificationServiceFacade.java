package xyz.aimcup.tournament.service.qualifications.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.stereotype.Component;
import xyz.aimcup.tournament.data.entity.qualification.QualificationType;
import xyz.aimcup.tournament.service.qualifications.QualificationService;

@Component
@RequiredArgsConstructor
public class QualificationServiceFacade {

    private final List<QualificationService> qualificationServices;

    public QualificationService getQualificationService(QualificationType qualificationType) {
        return qualificationServices.stream()
            .filter(qualificationService -> qualificationService
                .getQualificationType(qualificationType))
            .findFirst()
            .orElseThrow(() -> new NotReadablePropertyException(
                QualificationService.class,
                "qualificationType",
                "No QualificationService found for type: " + qualificationType));
    }

}
