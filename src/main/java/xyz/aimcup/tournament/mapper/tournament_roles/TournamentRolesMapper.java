package xyz.aimcup.tournament.mapper.tournament_roles;

import org.apache.commons.lang3.EnumUtils;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import xyz.aimcup.generated.model.CreateTournamentRoleRequestDto;
import xyz.aimcup.generated.model.TournamentRoleResponseDto;
import xyz.aimcup.tournament.data.entity.tournament_role.Permission;
import xyz.aimcup.tournament.data.entity.tournament_role.TournamentRole;
import xyz.aimcup.tournament.exception.BadRequestException;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TournamentRolesMapper {

    default List<Permission> mapPermissions(List<String> permissions) {
        List<Permission> p = new ArrayList<>();
        for (String permission : permissions) {
            if (EnumUtils.isValidEnum(Permission.class, permission)) {
                p.add(Permission.valueOf(permission));
            } else {
                throw new BadRequestException("Permission " + permission + " is not valid.");
            }
        }
        return p;
    }

    TournamentRole mapTournamentRoleFromCreateRequestDto(CreateTournamentRoleRequestDto createTournamentRoleRequestDto);

    TournamentRoleResponseDto mapTournamentRoleToResponseDto(TournamentRole tournamentRole);

    void updateTournamentRoleFromCreateRequestDto(@MappingTarget TournamentRole tournamentRole1, TournamentRole tournamentRole2);

    List<TournamentRoleResponseDto> mapTournamentRolesToResponseDtos(List<TournamentRole> tournamentRoles);
}
