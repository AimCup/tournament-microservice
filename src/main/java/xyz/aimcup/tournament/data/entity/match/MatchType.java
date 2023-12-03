package xyz.aimcup.tournament.data.entity.match;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum MatchType {

    TEAM_VS(MatchTypeNames.TEAM_VS),

    PARTICIPANT_VS(MatchTypeNames.PARTICIPANT_VS);


    @NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
    public static class MatchTypeNames {

        public static final String TEAM_VS = "TEAM_VS";
        public static final String PARTICIPANT_VS = "PARTICIPANT_VS";
    }

    MatchType(String value) {
        this.value = value;
    }

    private final String value;

}
