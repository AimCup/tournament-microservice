package xyz.aimcup.tournament.service.qualifications;

import xyz.aimcup.tournament.data.entity.qualification.QualificationType;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

public interface QualificationService {

    void createQualificationsFor(Tournament tournament);
    boolean isMatchingService(QualificationType qualificationType);
}
