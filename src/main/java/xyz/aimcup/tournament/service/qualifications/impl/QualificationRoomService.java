package xyz.aimcup.tournament.service.qualifications.impl;

import java.util.HashSet;
import org.springframework.stereotype.Service;
import xyz.aimcup.tournament.data.entity.qualification.QualificationRoom;
import xyz.aimcup.tournament.data.entity.qualification.QualificationType;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.service.qualifications.QualificationService;

@Service
public class QualificationRoomService implements QualificationService {

    @Override
    public void createQualificationsFor(Tournament tournament) {
        final var numberOfSpotsToCreate = tournament.getTournamentData()
            .calculateNumberOfQualificationSpots();
        final var participantsPerRoom = tournament.getTournamentData()
            .getParticipantsPerQualificationSpotLimit();
        var qualificationRooms = new HashSet<QualificationRoom>();

        for (int numberOfCreatedSpot = 0; numberOfCreatedSpot < numberOfSpotsToCreate;
            numberOfCreatedSpot++) {
            final var qualificationRoom = QualificationRoom.builder()
                .qualificationPhase(tournament.getQualificationPhase())
                .participantsLimit(participantsPerRoom)
                .build();
            qualificationRooms.add(qualificationRoom);
        }
        tournament.getQualificationPhase().setQualificationRooms(qualificationRooms);
    }

    @Override
    public boolean isMatchingService(QualificationType qualificationType) {
        return QualificationType.QUALIFICATION_ROOMS.equals(qualificationType);
    }
}
