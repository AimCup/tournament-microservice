package xyz.aimcup.tournament.service.qualifications;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.aimcup.tournament.data.entity.qualification.QualificationType;
import xyz.aimcup.tournament.service.qualifications.exceptions.QualificationServiceNotFoundException;

@Component
@RequiredArgsConstructor
public class QualificationServiceFacade {

    private final List<QualificationService> qualificationServices;

    public QualificationService getQualificationService(QualificationType qualificationType) {
        return qualificationServices.stream()
            .filter(qualificationService -> qualificationService
                .isMatchingService(qualificationType))
            .findFirst()
            .orElseThrow(() -> new QualificationServiceNotFoundException(qualificationType));
    }

}
