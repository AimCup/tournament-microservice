package xyz.aimcup.tournament.data.entity.match.builder;

import java.util.UUID;
import lombok.NoArgsConstructor;
import xyz.aimcup.tournament.data.entity.match.MatchType;
import xyz.aimcup.tournament.data.entity.match.ParticipantBasedMatch;
import xyz.aimcup.tournament.data.entity.phase.BracketsPhase;
import xyz.aimcup.tournament.data.entity.qualification.QualificationGroup;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ParticipantBasedMatchTestCaseBuilder {

    public static ParticipantBasedMatch buildMatchEntityAssignedToQualificationsGroup(
        UUID tournamentId, QualificationGroup qualificationGroup) {
        return ParticipantBasedMatch.builder()
            .tournamentId(tournamentId)
            .matchType(MatchType.PARTICIPANT_VS)
            .qualificationGroupId(qualificationGroup.getId())
            .qualificationGroup(qualificationGroup)
            .build();
    }

    public static ParticipantBasedMatch buildMatchEntityAssignedToBracketsPhase(
        UUID tournamentId, BracketsPhase bracketsPhase) {
        return ParticipantBasedMatch.builder()
            .tournamentId(tournamentId)
            .matchType(MatchType.PARTICIPANT_VS)
            .bracketsPhaseId(bracketsPhase.getId())
            .bracketsPhase(bracketsPhase)
            .build();
    }

    public static ParticipantBasedMatch buildMatchWith(UUID tournamentId) {
        return ParticipantBasedMatch.builder()
            .tournamentId(tournamentId)
            .matchType(MatchType.PARTICIPANT_VS)
            .build();
    }
}
