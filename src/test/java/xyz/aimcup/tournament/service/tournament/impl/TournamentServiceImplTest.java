package xyz.aimcup.tournament.service.tournament.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import xyz.aimcup.tournament.data.repository.tournament.TournamentRepository;

@ExtendWith(MockitoExtension.class)
class TournamentServiceImplTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @InjectMocks
    private TournamentServiceImpl tournamentService;

    @Test
    void shouldThrow404ResponseStatusExceptionWhenNoTournamentWasFoundById() {
        //given
        final var tournamentId = UUID.randomUUID();
        given(tournamentRepository.findById(tournamentId)).willReturn(Optional.empty());

        //when and then
        assertThatThrownBy(() -> tournamentService.getTournamentById(tournamentId))
            .isInstanceOf(ResponseStatusException.class)
            .hasMessageContaining("Tournament with id " + tournamentId + " not found");

    }
}