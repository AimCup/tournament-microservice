package xyz.aimcup.tournament.data.repository.participants;

import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.aimcup.tournament.data.entity.participant.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, UUID> {

    Set<Participant> findAllByIdInAndTournament_Id(Set<UUID> participantIds, UUID tournamentId);

}
