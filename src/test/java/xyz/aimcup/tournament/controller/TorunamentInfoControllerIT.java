package xyz.aimcup.tournament.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static xyz.aimcup.tournament.testingtools.PatternTestTools.UUID_PATTERN;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.util.ResourceUtils;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import xyz.aimcup.generated.model.TournamentResponseDto;
import xyz.aimcup.tournament.controller.mvc.TournamentInfoMockMvc;
import xyz.aimcup.tournament.controller.mvc.TournamentsMockMvc;
import xyz.aimcup.tournament.reusablecontainers.TestContainersFullSetupIT;

@AutoConfigureMockMvc(addFilters = false)
class TorunamentInfoControllerIT extends TestContainersFullSetupIT {

    @Autowired
    private TournamentsMockMvc tournamentMockMvc;

    @Autowired
    private TournamentInfoMockMvc tournamentsInfoMockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldUpdateTournamentInfoProperly() throws Exception {
        //given
        UUID createdTournamentUuid = tournamentMockMvc
            .createTournament("classpath:./requests/CreateTournamentRequest.json");

        TournamentResponseDto expectedResponse = objectMapper.readValue(
            FileUtils.readFileToString(ResourceUtils.getFile(
                    "classpath:./responses/TournamentWithTournamentInfoResponse.json"),
                StandardCharsets.UTF_8)
        .replaceAll(UUID_PATTERN, ""), TournamentResponseDto.class);

        //when
        tournamentsInfoMockMvc.createTournamentInfo(createdTournamentUuid,
            "classpath:./requests/CreateTournamentInfoRequest.json");

        TournamentResponseDto tournamentWithTournamentInfoResponse = tournamentMockMvc
            .getTournamentByIdWithoutUuids(createdTournamentUuid, true);

        //then
        assertThat(tournamentWithTournamentInfoResponse)
            .usingRecursiveComparison()
            .ignoringFields("tournamentInfo.createdAt")
            .isEqualTo(expectedResponse);

        assertThat(tournamentWithTournamentInfoResponse.getTournamentInfo().getCreatedAt())
            .isAfter(LocalDateTime.now().minusMinutes(1));

    }
}