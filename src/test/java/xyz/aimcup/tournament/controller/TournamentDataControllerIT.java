package xyz.aimcup.tournament.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static xyz.aimcup.tournament.testingtools.PatternTestTools.UUID_PATTERN;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.util.ResourceUtils;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import xyz.aimcup.generated.model.TournamentResponseDto;
import xyz.aimcup.tournament.controller.mvc.TournamentDataMockMvc;
import xyz.aimcup.tournament.controller.mvc.TournamentsMockMvc;
import xyz.aimcup.tournament.reusablecontainers.TestContainersFullSetupIT;

@AutoConfigureMockMvc(addFilters = false)
class TournamentDataControllerIT extends TestContainersFullSetupIT {

    @Autowired
    private TournamentsMockMvc tournamentMockMvc;

    @Autowired
    private TournamentDataMockMvc tournamentDataMockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldUpdateTournamentDataProperly() throws Exception {
        //given
        UUID createdTournamentUuid = tournamentMockMvc
            .createTournament("classpath:./requests/CreateTournamentRequest.json");

        TournamentResponseDto expectedResponse = objectMapper.readValue(
            FileUtils.readFileToString(ResourceUtils.getFile(
                        "classpath:./responses/TournamentWithTournamentDataResponse.json"),
                    StandardCharsets.UTF_8)
                .replaceAll(UUID_PATTERN, ""), TournamentResponseDto.class);
        //when
        tournamentDataMockMvc.updateTournamentData(createdTournamentUuid,
            "classpath:./requests/UpdateTournamentDataRequest.json");

        TournamentResponseDto tournamentWithModifiedTournamentData = tournamentMockMvc
            .getTournamentByIdWithoutUuids(createdTournamentUuid, true);

        //then
        assertThat(tournamentWithModifiedTournamentData)
            .usingRecursiveComparison()
            .isEqualTo(expectedResponse);

    }

}