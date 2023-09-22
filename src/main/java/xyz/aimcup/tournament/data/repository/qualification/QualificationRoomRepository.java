package xyz.aimcup.tournament.data.repository.qualification;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.aimcup.tournament.data.entity.qualification.QualificationRoom;

public interface QualificationRoomRepository extends JpaRepository<QualificationRoom, UUID> {

}
