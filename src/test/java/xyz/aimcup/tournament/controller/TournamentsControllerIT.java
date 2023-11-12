package xyz.aimcup.tournament.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import jakarta.servlet.Filter;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import xyz.aimcup.tournament.reusablecontainers.TestContainersFullSetupIT;

@AutoConfigureMockMvc(addFilters = false)
class TournamentsControllerIT extends TestContainersFullSetupIT {

    final String UUID_PATTERN = "([0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12})";

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldCreateTournamentProperly() throws Exception {
        //given and when
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
        assertThat(tournamentCreateResponse).containsPattern(UUID_PATTERN);
    }

    @Test
    void shouldGetTournamentProperly() throws Exception {
        //given
        String createdTorunamentUUID = mockMvc
            .perform(post("/tournaments")
                .contentType("application/json")
                .content(FileUtils.readFileToString(
                    ResourceUtils.getFile("classpath:./requests/CreateTournamentRequest.json"),
                    StandardCharsets.UTF_8)))
            .andReturn()
            .getResponse()
            .getContentAsString()
            .replaceAll("\"", "");

        //when
        MvcResult mvcResult = mockMvc.perform(get("/tournaments/{tournamentId}", createdTorunamentUUID)
                .contentType("application/json"))
            .andExpect(status().is(200))
            .andReturn();

        String tournamentGetResponseWithoutUuids = mvcResult.getResponse().getContentAsString()
            .replaceAll(UUID_PATTERN,"");
        String expectedResponseWithoutUuids = FileUtils.readFileToString(
            ResourceUtils.getFile("classpath:./dtos/TournamentResponseDto.json"), StandardCharsets.UTF_8)
            .replaceAll(UUID_PATTERN, "");

        //then
        assertThat(tournamentGetResponseWithoutUuids).isEqualToIgnoringWhitespace(expectedResponseWithoutUuids);
    }
}