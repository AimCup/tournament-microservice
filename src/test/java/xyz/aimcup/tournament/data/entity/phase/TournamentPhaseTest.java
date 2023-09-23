package xyz.aimcup.tournament.data.entity.phase;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;


class TournamentPhaseTest {

    @Test
    void shouldReturnTrueSigningEnabledFlagWhenCurrentDateIsBetweenStartTimeAndEndTime() {
        // given
        final var qualificationPhase = QualificationPhase.builder()
            .startTime(LocalDateTime.now().minusDays(1))
            .endTime(LocalDateTime.now().plusDays(1))
            .build();
        // when
        final var isSigningEnabled = qualificationPhase.isPhaseActiveNow();

        // then
        assertThat(isSigningEnabled).isTrue();
    }

    @Test
    void shouldReturnFalseSigningEnabledFlagWhenCurrentDateIsAfterStartTimeAndEndTime() {
        // given
        final var qualificationPhase = QualificationPhase.builder()
            .startTime(LocalDateTime.now().minusDays(3))
            .endTime(LocalDateTime.now().minusDays(2))
            .build();
        // when
        final var isSigningEnabled = qualificationPhase.isPhaseActiveNow();

        // then
        assertThat(isSigningEnabled).isFalse();
    }

    @Test
    void shouldReturnFalseSigningEnabledFlagWhenCurrentDateIsBeforeStartTimeAndEndTime() {
        // given
        final var qualificationPhase = QualificationPhase.builder()
            .startTime(LocalDateTime.now().plusDays(2))
            .endTime(LocalDateTime.now().plusDays(3))
            .build();
        // when
        final var isSigningEnabled = qualificationPhase.isPhaseActiveNow();

        // then
        assertThat(isSigningEnabled).isFalse();
    }
}