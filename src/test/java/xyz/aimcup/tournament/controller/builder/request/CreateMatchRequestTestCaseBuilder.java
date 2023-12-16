package xyz.aimcup.tournament.controller.builder.request;

import java.util.UUID;
import lombok.NoArgsConstructor;
import xyz.aimcup.generated.model.CreateMatchRequest;
import xyz.aimcup.generated.model.CreateMatchRequest.MatchTypeEnum;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CreateMatchRequestTestCaseBuilder {

    public static CreateMatchRequest buildCreateMatchRequestWithQualificationGroupId(
        UUID tournamentId,
        String matchType, UUID qualificationGroupId) {
        return CreateMatchRequest.builder()
            .tournamentId(tournamentId)
            .matchType(MatchTypeEnum.fromValue(matchType))
            .qualificationGroupId(qualificationGroupId)
            .build();
    }

    public static CreateMatchRequest buildCreateMatchRequestWithBracketPhaseId(UUID tournamentId,
        String matchType, UUID bracketPhaseId) {
        return CreateMatchRequest.builder()
            .tournamentId(tournamentId)
            .matchType(MatchTypeEnum.fromValue(matchType))
            .bracketsPhaseId(bracketPhaseId)
            .build();
    }
}
