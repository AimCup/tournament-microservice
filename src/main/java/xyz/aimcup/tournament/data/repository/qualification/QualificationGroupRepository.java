package xyz.aimcup.tournament.data.repository.qualification;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.aimcup.tournament.data.entity.qualification.QualificationGroup;

@Repository
public interface QualificationGroupRepository extends JpaRepository<QualificationGroup, UUID> {

}
