package xyz.aimcup.tournament.service.matches;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.aimcup.generated.model.CreateMatchRequest;
import xyz.aimcup.generated.model.CreateMatchRequest.MatchTypeEnum;
import xyz.aimcup.tournament.data.entity.match.MatchType;
import xyz.aimcup.tournament.service.matches.exceptions.MatchServiceNotFoundException;
import xyz.aimcup.tournament.service.matches.impl.MatchServiceImpl;

@ExtendWith(MockitoExtension.class)
class MatchServiceFacadeTest {

    @Mock
    private List<SpecificMatchService> matchSpecificServices;

    @Mock
    private MatchServiceImpl matchBaseService;

    @InjectMocks
    private MatchServiceFacade matchServiceFacade;

    @Test
    void shouldThrowMatchServiceNotFoundExceptionWhenCannotFindProperServiceImplForMatchTypeEnum() {
        //given
        final MatchTypeEnum matchType = MatchTypeEnum.PARTICIPANT_VS;
        final var  createMatchRequet = CreateMatchRequest.builder()
            .matchType(matchType)
            .build();

        // when and then
        assertThatThrownBy(() -> matchServiceFacade.createMatch(createMatchRequet))
            .isInstanceOf(MatchServiceNotFoundException.class)
            .hasMessage("No MatchService found for type: %s", matchType);
    }
}