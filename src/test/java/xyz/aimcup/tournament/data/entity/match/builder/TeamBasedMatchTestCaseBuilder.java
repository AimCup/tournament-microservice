package xyz.aimcup.tournament.data.entity.match.builder;

import java.util.UUID;
import lombok.NoArgsConstructor;
import xyz.aimcup.tournament.data.entity.match.MatchType;
import xyz.aimcup.tournament.data.entity.match.TeamBasedMatch;
import xyz.aimcup.tournament.data.entity.phase.BracketsPhase;
import xyz.aimcup.tournament.data.entity.qualification.QualificationGroup;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class TeamBasedMatchTestCaseBuilder {

    public static TeamBasedMatch buildMatchEntityAssignedToQualificationsGroup(
        UUID tournamentId, QualificationGroup qualificationGroup) {
        return TeamBasedMatch.builder()
            .tournamentId(tournamentId)
            .matchType(MatchType.TEAM_VS)
            .qualificationGroupId(qualificationGroup.getId())
            .qualificationGroup(qualificationGroup)
            .build();
    }

    public static TeamBasedMatch buildMatchEntityAssignedToBracketsPhase(
        UUID tournamentId, BracketsPhase bracketsPhase) {
        return TeamBasedMatch.builder()
            .tournamentId(tournamentId)
            .matchType(MatchType.TEAM_VS)
            .bracketsPhaseId(bracketsPhase.getId())
            .bracketsPhase(bracketsPhase)
            .build();
    }

    public static TeamBasedMatch buildMatchWith(UUID tournamentId) {
        return TeamBasedMatch.builder()
            .tournamentId(tournamentId)
            .matchType(MatchType.TEAM_VS)
            .build();
    }
}
