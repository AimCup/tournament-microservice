package xyz.aimcup.tournament.service.qualifications.impl;

import java.util.HashSet;
import org.springframework.stereotype.Service;
import xyz.aimcup.tournament.data.entity.qualification.QualificationGroup;
import xyz.aimcup.tournament.data.entity.qualification.QualificationType;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.service.qualifications.QualificationService;

@Service
public class GroupService implements QualificationService {

    @Override
    public void createQualificationsFor(Tournament tournament) {
        final var numberOfSpotsToCreate = tournament.calculateNumberOfQualificationSpots();
        final var participantsPerGroup = tournament.getTournamentConstraints()
            .getParticipantsPerQualificationSpotLimit();
        var qualificationGroups = new HashSet<QualificationGroup>();

        for (int numberOfCreatedSpot = 0; numberOfCreatedSpot < numberOfSpotsToCreate;
            numberOfCreatedSpot++) {
            qualificationGroups.add(
                QualificationGroup.builder()
                    .tournament(tournament)
                    .participantsLimit(participantsPerGroup)
                    .build()
            );
        }
        tournament.setQualificationGroups(qualificationGroups);
    }

    @Override
    public boolean getQualificationType(QualificationType qualificationType) {
        return QualificationType.GROUP_STAGES.equals(qualificationType);
    }
}
