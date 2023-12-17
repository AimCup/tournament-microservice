package xyz.aimcup.tournament.data.entity.qualification;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum QualificationRoomType {

    TEAM_VS(QualificationRoomTypeNames.TEAM_VS),

    PARTICIPANT_VS(QualificationRoomTypeNames.PARTICIPANT_VS);


    @NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
    public static class QualificationRoomTypeNames {

        public static final String TEAM_VS = "TEAM_VS";
        public static final String PARTICIPANT_VS = "PARTICIPANT_VS";
    }

    QualificationRoomType(String value) {
        this.value = value;
    }

    private final String value;

}
