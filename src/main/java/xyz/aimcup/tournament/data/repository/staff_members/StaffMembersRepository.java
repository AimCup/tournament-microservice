package xyz.aimcup.tournament.data.repository.staff_members;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.aimcup.tournament.data.entity.staff_member.StaffMember;

import java.util.List;
import java.util.UUID;

@Repository
public interface StaffMembersRepository extends JpaRepository<StaffMember, UUID> {

    Optional<StaffMember> findByIdAndTournament_Id(UUID id, UUID tournamentId);
    List<StaffMember> findAllByTournament_Id(UUID tournamentId);
    boolean existsByTournament_IdAndUserId(UUID tournamentId, UUID userId);
}
