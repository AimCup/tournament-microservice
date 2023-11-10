package xyz.aimcup.tournament.controller.builder.dto;

import java.util.UUID;
import lombok.NoArgsConstructor;
import xyz.aimcup.generated.model.MatchResponseDto;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class MatchResponseDtoTestCaseBuilder {

    public static MatchResponseDto buildMatchResponseDtoWithQualificationGroupId(
        UUID matchId, String matchType, UUID qualificationGroupId) {
        final var matchResponseDto = MatchResponseDto.builder()
            .id(matchId)
            .matchType(matchType)
            .qualificationGroupId(qualificationGroupId)
            .bracketsPhaseId(null)
            .build();
        setDefaultMatchResponseDtoValues(matchResponseDto);
        return matchResponseDto;
    }

    public static MatchResponseDto buildMatchResponseDtoWithBracketPhaseId(
        UUID matchId, String matchType, UUID bracketPhaseId) {
        final var matchResponseDto = MatchResponseDto.builder()
            .id(matchId)
            .matchType(matchType)
            .qualificationGroupId(null)
            .bracketsPhaseId(bracketPhaseId)
            .build();
        setDefaultMatchResponseDtoValues(matchResponseDto);
        return matchResponseDto;
    }

    private static void setDefaultMatchResponseDtoValues(
        MatchResponseDto matchResponseDto) {
        matchResponseDto.setRefereesLimit(1);
        matchResponseDto.setCommentatorsLimit(2);
        matchResponseDto.setStreamersLimit(3);
    }


}
