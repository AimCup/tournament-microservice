package xyz.aimcup.tournament.controller.builder.dto;

import java.util.UUID;
import lombok.NoArgsConstructor;
import xyz.aimcup.generated.model.MatchResponseDto;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class MatchResponseDtoTestCaseBuilder {

    public static MatchResponseDto buildMatchResponseDtoWithQualificationGroupId(
        UUID matchId, String matchType, UUID qualificationGroupId) {
        return MatchResponseDto.builder()
            .id(matchId)
            .matchType(matchType)
            .qualificationGroupId(qualificationGroupId)
            .bracketsPhaseId(null)
            .build();
    }

    public static MatchResponseDto buildMatchResponseDtoWithBracketPhaseId(
        UUID matchId, String matchType, UUID bracketPhaseId) {
        return MatchResponseDto.builder()
            .id(matchId)
            .matchType(matchType)
            .qualificationGroupId(null)
            .bracketsPhaseId(bracketPhaseId)
            .build();
    }

}
