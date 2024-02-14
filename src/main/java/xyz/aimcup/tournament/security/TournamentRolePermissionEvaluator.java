package xyz.aimcup.tournament.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament_role.Permission;
import xyz.aimcup.tournament.service.tournament.TournamentService;

import java.io.Serializable;
import java.util.UUID;

// TODO: Implement when StaffMember is implemented
@RequiredArgsConstructor
public class TournamentRolePermissionEvaluator implements PermissionEvaluator {
    private final TournamentService tournamentService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof Permission)) {
            return false;
        }
        if (targetDomainObject instanceof UUID tournamentId) {
            return hasTournamentRolePermission(authentication, tournamentId, (Permission) permission);
        } else if (targetDomainObject instanceof Tournament tournament) {
            return hasTournamentRolePermission(authentication, tournament, (Permission) permission);
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new RuntimeException("Not implemented");
    }

    private boolean hasTournamentRolePermission(Authentication authentication, UUID tournamentId, Permission permission) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        return hasTournamentRolePermission(authentication, tournament, permission);
    }

    private boolean hasTournamentRolePermission(Authentication authentication, Tournament tournament, Permission permission) {
        return false;
    }
}
