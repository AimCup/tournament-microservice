package xyz.aimcup.tournament.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static xyz.aimcup.tournament.testingtools.PatternTestTools.UUID_PATTERN;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.util.ResourceUtils;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import xyz.aimcup.generated.model.SearchTournamentsDto;
import xyz.aimcup.generated.model.TournamentResponseDto;
import xyz.aimcup.tournament.controller.mvc.TournamentsMockMvc;
import xyz.aimcup.tournament.reusablecontainers.TestContainersFullSetupIT;

@AutoConfigureMockMvc(addFilters = false)
class TournamentsControllerIT extends TestContainersFullSetupIT {

    @Autowired
    private TournamentsMockMvc tournamentMockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateTournamentProperly() throws Exception {
        //given and when
        UUID tournamentCreateResponse = tournamentMockMvc
            .createTournament("classpath:./requests/CreateTournamentRequest.json");

        //then
        assertThat(tournamentCreateResponse.toString()).containsPattern(UUID_PATTERN);
    }

    @Test
    void shouldGetTournamentProperly() throws Exception {
        //given
        UUID createdTorunamentUUID = tournamentMockMvc
            .createTournament("classpath:./requests/CreateTournamentRequest.json");

        TournamentResponseDto expectedResponseWithoutUuids = objectMapper.readValue(
            FileUtils.readFileToString(ResourceUtils.getFile(
                        "classpath:./responses/TournamentResponse.json"),
                    StandardCharsets.UTF_8)
                .replaceAll(UUID_PATTERN, ""), TournamentResponseDto.class);

        //when
        TournamentResponseDto tournamentGetResponseWithoutUuids = tournamentMockMvc
            .getTournamentByIdWithoutUuids(createdTorunamentUUID, true);

        //then
        assertThat(tournamentGetResponseWithoutUuids)
            .usingRecursiveComparison()
            .isEqualTo(expectedResponseWithoutUuids);
    }

    @Test
    void shouldGetListOfTournamentsProperly() throws Exception {
        //given
        cleanTournamentRepository();
        for (int i = 0; i < 2; i++) {
            tournamentMockMvc
                .createTournament("classpath:./requests/CreateTournamentRequest.json");
        }

        List<SearchTournamentsDto> expectedResponseWithoutUuids = objectMapper.readValue(
            FileUtils.readFileToString(
                    ResourceUtils.getFile("classpath:./responses/ListOfTournamentsResponse.json"),
                    StandardCharsets.UTF_8)
                .replaceAll(UUID_PATTERN, ""), new TypeReference<>() {
            });

        //when
        List<SearchTournamentsDto> tournamentsGetResponseWithoutUuids = tournamentMockMvc
            .getListOfTournamentsWithoutUuids(0L, 2L, true);

        //then
        assertThat(tournamentsGetResponseWithoutUuids)
            .usingRecursiveComparison()
            .isEqualTo(expectedResponseWithoutUuids);
    }
}