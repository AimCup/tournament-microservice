package xyz.aimcup.tournament.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.ResourceUtils;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import xyz.aimcup.tournament.reusablecontainers.TestContainersFullSetupIT;

@AutoConfigureMockMvc
class TournamentsControllerTest extends TestContainersFullSetupIT {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldCreateTournamentProperly() throws Exception {
        //given and when
        final var uuidPattern = "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$";
        MvcResult mvcResult = mockMvc.perform(post("/tournaments")
                .contentType("application/json")
                .content(FileUtils.readFileToString(
                    ResourceUtils.getFile("classpath:./requests/CreateTournamentRequest.json"),
                    StandardCharsets.UTF_8)))
            .andExpect(status().is(201))
            .andReturn();

        String tournamentCreateResponse = mvcResult.getResponse().getContentAsString();

        //then
        tournamentCreateResponse = tournamentCreateResponse.replaceAll("\"", "");
        assertThat(tournamentCreateResponse).containsPattern(uuidPattern);
    }
}