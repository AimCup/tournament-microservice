package xyz.aimcup.tournament.data.entity.match;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xyz.aimcup.tournament.data.entity.phase.BracketsPhase;
import xyz.aimcup.tournament.data.entity.qualification.QualificationGroup;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchTestCaseBuilder {

    public static Match buildMatchEntityAssignedToQualificationsGroup(
        UUID tournamentId, QualificationGroup qualificationGroup, MatchType matchType) {
        final var match = buildMatchWith(tournamentId, matchType);
        match.setQualificationGroupId(qualificationGroup.getId());
        match.setQualificationGroup(qualificationGroup);
        return match;
    }

    public static Match buildMatchEntityAssignedToBracketsPhase(
        UUID tournamentId, BracketsPhase bracketsPhase, MatchType matchType) {
        final var match = buildMatchWith(tournamentId, matchType);
        match.setBracketsPhaseId(bracketsPhase.getId());
        match.setBracketsPhase(bracketsPhase);
        return match;
    }

    public static Match buildMatchWith(UUID tournamentId, MatchType matchType){
        return Match.builder()
            .tournamentId(tournamentId)
            .matchType(matchType)
            .refereesLimit(1)
            .commentatorsLimit(2)
            .streamersLimit(3)
            .build();
    }

}