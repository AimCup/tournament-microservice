//package xyz.aimcup.tournament.data.entity.tournament;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.jupiter.api.Test;
//
//class TournamentDataTest {
//
//    @Test
//    void shouldCalculateNumberOfQualificationSpotsProperly() {
//        //given
//        final var tournamentData = TournamentData.builder()
//            .participantsLimit(7)
//            .participantsPerQualificationSpotLimit(3)
//            .build();
//
//        //when
//        final var qualificationSpots = tournamentData.calculateNumberOfQualificationSpots();
//
//        //then
//        assertThat(qualificationSpots).isEqualTo(3);
//    }
//}