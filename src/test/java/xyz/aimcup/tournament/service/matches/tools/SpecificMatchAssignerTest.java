package xyz.aimcup.tournament.service.matches.tools;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.aimcup.tournament.data.entity.match.ParticipantBasedMatch;
import xyz.aimcup.tournament.data.entity.participant.Participant;
import xyz.aimcup.tournament.data.repository.participants.ParticipantRepository;
import xyz.aimcup.tournament.data.repository.phases.BracketsPhaseRepository;
import xyz.aimcup.tournament.data.repository.qualification.QualificationGroupRepository;
import xyz.aimcup.tournament.service.participants.exceptions.ParticipantNotAssignedException;
import xyz.aimcup.tournament.service.phases.exceptions.PhaseNotFoundException;
import xyz.aimcup.tournament.service.qualifications.exceptions.QualificationGroupNotFoundException;

@ExtendWith(MockitoExtension.class)
class SpecificMatchAssignerTest {

    @Mock
    private QualificationGroupRepository qualificationGroupRepository;

    @Mock
    private BracketsPhaseRepository bracketsPhaseRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private SpecificMatchAssigner specificMatchAssigner;

    @Test
    void shouldThrowQualificationGroupNotFoundExceptionWhenCannotFindQualificationGroupMatchingForProvidedMatch() {
        //given
        final var qualificationGroupId = UUID.fromString("7b47e4be-c600-4453-a5f3-0cb2a34744d5");
        final var match = ParticipantBasedMatch.builder()
            .qualificationGroupId(qualificationGroupId)
            .build();

        //when and then
        when(qualificationGroupRepository.findById(qualificationGroupId))
            .thenReturn(Optional.empty());
        assertThatThrownBy(() -> specificMatchAssigner.assignQualificationGroupToMatch(match))
            .isInstanceOf(QualificationGroupNotFoundException.class)
            .hasMessage(
                "No Qualification group found for id: 7b47e4be-c600-4453-a5f3-0cb2a34744d5");
    }

    @Test
    void shouldThrowPhaseNotFoundExceptionWhenCannotFindBracketsPhaseForProvidedMatch() {
        //given
        final var bracketsPhaseId = UUID.fromString("c9c300a8-aee1-4cf7-a194-773314a84495");
        final var match = ParticipantBasedMatch.builder()
            .bracketsPhaseId(bracketsPhaseId)
            .build();

        //when and then
        when(bracketsPhaseRepository.findById(bracketsPhaseId))
            .thenReturn(Optional.empty());
        assertThatThrownBy(() -> specificMatchAssigner.assignBracketPhaseToMatch(match))
            .isInstanceOf(PhaseNotFoundException.class)
            .hasMessage(
                "No BRACKETS phase found with given id: c9c300a8-aee1-4cf7-a194-773314a84495");
    }

    @Test
    void shouldThrowParticipantNotAssignedExceptionWhenNotAllProvidedParticipantsCanBeAssignedForProvidedMatch() {
        //given
        final var tournamentId = UUID.fromString("f01e4d2e-f694-4256-b89c-49df16115ab6");
        final var participantId1 = UUID.fromString("0e5dfdbf-5099-46df-992a-6dc925970a9e");
        final var participantId2 = UUID.fromString("1c726508-9c92-4424-91a9-53a5748f27d6");
        final var participantId3 = UUID.fromString("274efe44-7de2-41d6-b5f6-3cdc0db79c05");
        final var participant1 = Participant.builder().id(participantId1).build();
        final var participant2 = Participant.builder().id(participantId2).build();
        final var participantsIds = Set.of(participantId1, participantId2, participantId3);
        final var match = ParticipantBasedMatch.builder()
            .tournamentId(tournamentId)
            .build();

        //when and then
        given(participantRepository.findAllByIdInAndTournament_Id(participantsIds, tournamentId))
            .willReturn(Set.of(participant1, participant2));

        assertThatThrownBy(
            () -> specificMatchAssigner.assignParticipantsToMatch(match, participantsIds))
            .isInstanceOf(ParticipantNotAssignedException.class)
            .hasMessage(
                "Some participants were not found for TOURNAMENT: %s: [%s, %s, %s], found: [%s, %s]"
                    .formatted(tournamentId, participantId1, participantId2, participantId3,
                        participantId1, participantId2));
    }
}