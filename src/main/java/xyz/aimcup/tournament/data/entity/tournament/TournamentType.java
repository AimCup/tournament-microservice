package xyz.aimcup.tournament.data.entity.tournament;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum TournamentType {
    TEAM_VS(TournamentTypeNames.TEAM_VS),
    PARTICIPANT_VS(TournamentTypeNames.PARTICIPANT_VS),
    INTERNATIONAL(TournamentTypeNames.INTERNATIONAL);


    @NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
    public static class TournamentTypeNames {

        public static final String TEAM_VS = "TEAM_VS";
        public static final String PARTICIPANT_VS = "PARTICIPANT_VS";
        public static final String INTERNATIONAL = "INTERNATIONAL";
    }

    TournamentType(String value) {
        this.value = value;
    }

    private final String value;

}
