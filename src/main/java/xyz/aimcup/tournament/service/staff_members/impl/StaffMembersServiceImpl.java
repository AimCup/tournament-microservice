package xyz.aimcup.tournament.service.staff_members.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.aimcup.tournament.data.entity.staff_member.StaffMember;
import xyz.aimcup.tournament.data.repository.staff_members.StaffMembersRepository;
import xyz.aimcup.tournament.service.staff_members.StaffMembersService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StaffMembersServiceImpl implements StaffMembersService {
    private final StaffMembersRepository staffMembersRepository;
    @Override
    public List<StaffMember> findAllStaffMembersInTournament(UUID tournamentId) {
        return staffMembersRepository.findAllByTournament_Id(tournamentId);
    }
}
