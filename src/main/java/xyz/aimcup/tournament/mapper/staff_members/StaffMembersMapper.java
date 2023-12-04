package xyz.aimcup.tournament.mapper.staff_members;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import xyz.aimcup.generated.model.StaffMemberResponseDto;
import xyz.aimcup.tournament.data.entity.staff_member.StaffMember;
import xyz.aimcup.tournament.data.entity.tournament_role.TournamentRole;
import xyz.aimcup.tournament.mapper.tournament_roles.TournamentRolesMapper;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TournamentRolesMapper.class})
public interface StaffMembersMapper {


    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapTournamentRoleToResponseDto")
    StaffMemberResponseDto toStaffMemberResponse(StaffMember staffMember);

    List<StaffMemberResponseDto> toStaffMemberResponse(List<StaffMember> staffMembers);
}
