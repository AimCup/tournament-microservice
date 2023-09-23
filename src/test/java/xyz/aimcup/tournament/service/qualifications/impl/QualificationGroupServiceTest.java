package xyz.aimcup.tournament.service.qualifications.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.aimcup.tournament.data.entity.phase.QualificationPhase;
import xyz.aimcup.tournament.data.entity.qualification.QualificationGroup;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament.TournamentData;

@ExtendWith(MockitoExtension.class)
class QualificationGroupServiceTest {

    @InjectMocks
    private QualificationGroupService qualificationGroupService;

    @Test
    void shouldCreateProperQualificationGroupsForTournamentEntity() {
        // given
        var tournament = Mockito.mock(Tournament.class);
        var qualificationPhase = new QualificationPhase();

        given(tournament.getTournamentData()).willReturn(Mockito.mock(TournamentData.class));
        given(tournament.getTournamentData().calculateNumberOfQualificationSpots())
            .willReturn(5);
        given(tournament.getTournamentData().getParticipantsPerQualificationSpotLimit())
            .willReturn(2);
        given(tournament.getQualificationPhase()).willReturn(qualificationPhase);

        final var expectedQualificationGroup =
            QualificationGroup.builder()
                .qualificationPhase(qualificationPhase)
                .participantsLimit(2)
                .build();
        // when
        qualificationGroupService.createQualificationsFor(tournament);

        // then
        assertThat(tournament.getQualificationPhase().getQualificationGroups())
            .hasSize(5);
        assertThat(tournament.getQualificationPhase().getQualificationGroups())
            .element(0)
            .usingRecursiveComparison()
            .isEqualTo(expectedQualificationGroup);
    }

}