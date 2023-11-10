package xyz.aimcup.tournament.service.matches.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.aimcup.tournament.controller.builder.request.CreateMatchRequestTestCaseBuilder;
import xyz.aimcup.tournament.data.entity.match.Match;
import xyz.aimcup.tournament.data.entity.match.builder.MatchTestCaseBuilder;
import xyz.aimcup.tournament.data.entity.match.MatchType;
import xyz.aimcup.tournament.data.repository.match.MatchRepository;
import xyz.aimcup.tournament.mapper.match.MatchMapperImpl;
import xyz.aimcup.tournament.service.matches.tools.SpecificMatchAssigner;

@ExtendWith(MockitoExtension.class)
class PlayerBasedMatchServiceTest {

    @Spy
    private MatchMapperImpl matchMapper;

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private SpecificMatchAssigner specificMatchAssigner;

    @InjectMocks
    private PlayerBasedMatchService playerBasedMatchService;

    @Test
    void shouldCreatePlayerBasedMatchAndMapMatchProperly() {
        // given
        ArgumentCaptor<Match> matchArgumentCaptor = ArgumentCaptor.forClass(Match.class);

        final var qualificationGroupId = UUID.fromString("c9f9cd37-9033-4f03-9a7a-dc85309c0722");
        final var tournamentId = UUID.fromString("1b2976ab-ac68-4b12-bdbe-5e9c2cb43b05");

        final var match = MatchTestCaseBuilder.buildMatchWith(tournamentId, MatchType.PLAYER_VS);
        match.setQualificationGroupId(qualificationGroupId);

        final var createMatchRequest = CreateMatchRequestTestCaseBuilder
            .buildCreateMatchRequestWithQualificationGroupId(
                tournamentId, MatchType.MatchTypeNames.PLAYER_VS, qualificationGroupId);

        //when
        playerBasedMatchService.createMatch(createMatchRequest);

        // then
        verify(specificMatchAssigner, times(1)).assignBracketPhaseToMatch(any(Match.class));
        verify(specificMatchAssigner, times(1)).assignQualificationGroupToMatch(any(Match.class));
        verify(specificMatchAssigner, times(1))
            .assignParticipantsToMatch(any(Match.class), eq(createMatchRequest.getParticipantsIds()));
        verify(matchRepository, times(1)).save(matchArgumentCaptor.capture());

        assertThat(matchArgumentCaptor.getValue()).usingRecursiveComparison()
            .isEqualTo(match);
    }

    @Test
    void shouldReturnTrueIfIsPlayerVs(){
        //given
        final var matchType = MatchType.PLAYER_VS;
        //when and then
        assertThat(playerBasedMatchService.isMatchingService(matchType)).isTrue();
    }

}