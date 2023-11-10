package xyz.aimcup.tournament.data.entity.participant;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum ParticipantType {

    PLAYER(MatchTypeNames.PLAYER),
    TEAM(MatchTypeNames.TEAM),
    REFEREE(MatchTypeNames.REFEREE),
    COMMENTATOR(MatchTypeNames.COMMENTATOR),
    STREAMER(MatchTypeNames.STREAMER);

    @NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
    public static class MatchTypeNames {

        public static final String PLAYER = "PLAYER";
        public static final String TEAM = "TEAM";
        public static final String REFEREE = "REFEREE";
        public static final String COMMENTATOR = "COMMENTATOR";
        public static final String STREAMER = "STREAMER";
    }

    ParticipantType(String value) {
        this.value = value;
    }

    private final String value;

}
