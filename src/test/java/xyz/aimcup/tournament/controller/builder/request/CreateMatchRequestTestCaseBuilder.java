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
        final var createMatchRequest = CreateMatchRequest.builder()
            .tournamentId(tournamentId)
            .matchType(MatchTypeEnum.fromValue(matchType))
            .qualificationGroupId(qualificationGroupId)
            .build();
        setDefaultCreateMatchRequestValues(createMatchRequest);
        return createMatchRequest;
    }

    public static CreateMatchRequest buildCreateMatchRequestWithBracketPhaseId(UUID tournamentId,
        String matchType, UUID bracketPhaseId) {
        final var createMatchRequest = CreateMatchRequest.builder()
            .tournamentId(tournamentId)
            .matchType(MatchTypeEnum.fromValue(matchType))
            .bracketsPhaseId(bracketPhaseId)
            .build();
        setDefaultCreateMatchRequestValues(createMatchRequest);
        return createMatchRequest;
    }

    private static void setDefaultCreateMatchRequestValues(
        CreateMatchRequest createMatchRequest) {
        createMatchRequest.setRefereesLimit(1);
        createMatchRequest.setCommentatorsLimit(2);
        createMatchRequest.setStreamersLimit(3);
    }
}
