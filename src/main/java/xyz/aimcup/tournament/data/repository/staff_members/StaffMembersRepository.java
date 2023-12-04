package xyz.aimcup.tournament.data.repository.staff_members;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.aimcup.tournament.data.entity.staff_member.StaffMember;

import java.util.List;
import java.util.UUID;

@Repository
public interface StaffMembersRepository extends JpaRepository<StaffMember, UUID> {
    List<StaffMember> findAllByTournament_Id(UUID tournamentId);
}
