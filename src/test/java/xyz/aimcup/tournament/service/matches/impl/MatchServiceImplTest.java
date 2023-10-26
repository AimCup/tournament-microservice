package xyz.aimcup.tournament.service.matches.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static xyz.aimcup.tournament.data.entity.match.MatchType.MatchTypeNames.PLAYER_VS;
import static xyz.aimcup.tournament.data.entity.match.MatchType.MatchTypeNames.TEAM_VS;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import xyz.aimcup.generated.model.SearchMatchRequest;
import xyz.aimcup.tournament.controller.builder.dto.MatchResponseDtoTestCaseBuilder;
import xyz.aimcup.tournament.data.entity.match.MatchTestCaseBuilder;
import xyz.aimcup.tournament.data.entity.match.MatchType;
import xyz.aimcup.tournament.data.entity.phase.BracketsPhase;
import xyz.aimcup.tournament.data.entity.qualification.QualificationGroup;
import xyz.aimcup.tournament.data.repository.match.MatchRepository;
import xyz.aimcup.tournament.mapper.match.MatchMapperImpl;

@ExtendWith(MockitoExtension.class)
class MatchServiceImplTest {

    @Mock
    private MatchRepository matchRepository;

    @Spy
    private MatchMapperImpl matchMapper;

    @InjectMocks
    private MatchServiceImpl matchServiceImpl;

    @Test
    void shouldGetMatchesForQualificationGroupAndMapThemProperly() {
        //given
        final var qualificationGroupId = UUID.fromString("8b9b32c7-5c38-42ba-82fa-703e31bb8cd2");
        final var qualificationGroup = QualificationGroup.builder()
            .id(qualificationGroupId)
            .build();
        final var searchMatchRequest = SearchMatchRequest.builder()
            .qualificationGroupId(qualificationGroupId)
            .build();

        final var match = MatchTestCaseBuilder.buildMatchEntityAssignedToQualificationsGroup(
            UUID.randomUUID(), qualificationGroup, MatchType.PLAYER_VS);

        //when
        when(matchRepository.findAll(any(Specification.class))).thenReturn(List.of(match));
        final var matchesFound = matchServiceImpl.getMatches(searchMatchRequest);

        //then
        assertThat(matchesFound.get(0)).usingRecursiveAssertion()
            .isEqualTo(
                MatchResponseDtoTestCaseBuilder.buildMatchResponseDtoWithQualificationGroupId(
                    match.getId(), PLAYER_VS, qualificationGroupId));
    }

    @Test
    void shouldGetMatchesForBracketsPhaseAndMapThemProperly() {
        //given
        final var bracketsPhaseId = UUID.fromString("1b9b32c7-5c38-42ba-82fa-703e31bb8cd2");
        final var bracketPhase = BracketsPhase.builder()
            .id(bracketsPhaseId)
            .build();
        final var searchMatchRequest = SearchMatchRequest.builder()
            .bracketsPhaseId(bracketsPhaseId)
            .build();

        final var match = MatchTestCaseBuilder.buildMatchEntityAssignedToBracketsPhase(
            UUID.randomUUID(), bracketPhase, MatchType.TEAM_VS);

        //when
        when(matchRepository.findAll(any(Specification.class))).thenReturn(List.of(match));
        final var matchesFound = matchServiceImpl.getMatches(searchMatchRequest);

        //then
        assertThat(matchesFound.get(0)).usingRecursiveAssertion()
            .isEqualTo(MatchResponseDtoTestCaseBuilder.buildMatchResponseDtoWithBracketPhaseId(
                match.getId(), TEAM_VS, bracketsPhaseId));
    }

}