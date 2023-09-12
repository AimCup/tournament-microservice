package xyz.aimcup.tournament.data.entity.tournament;

import lombok.Getter;

@Getter
public enum TournamentType {
    TEAM_VS(TournamentTypeNames.TEAM_VS),
    PLAYER_VS(TournamentTypeNames.PLAYER_VS),
    INTERNATIONAL(TournamentTypeNames.INTERNATIONAL);

    public static class TournamentTypeNames {

        public static final String TEAM_VS = "team_vs";
        public static final String PLAYER_VS = "player_vs";
        public static final String INTERNATIONAL = "international";
    }

    TournamentType(String value) {
        this.value = value;
    }

    private final String value;

}
