package xyz.aimcup.tournament.data.repository.phases;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.aimcup.tournament.data.entity.phase.BracketsPhase;

@Repository
public interface BracketsPhaseRepository extends JpaRepository<BracketsPhase, UUID> {

}
