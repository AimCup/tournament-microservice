package xyz.aimcup.tournament.controller.mvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static xyz.aimcup.tournament.testingtools.PatternTestTools.UUID_PATTERN;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import xyz.aimcup.generated.model.SearchTournamentsDto;
import xyz.aimcup.generated.model.TournamentResponseDto;
import xyz.aimcup.tournament.testingtools.MockMvcTools;

@Component
@ActiveProfiles("test")
public class TournamentsMockMvc {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public UUID createTournament(String requestPath) throws Exception {
        return UUID.fromString(mockMvc.perform(post("/tournaments")
                .contentType("application/json")
                .content(FileUtils.readFileToString(
                    ResourceUtils.getFile(requestPath),
                    StandardCharsets.UTF_8)))
            .andExpect(status().is(201))
            .andReturn()
            .getResponse()
            .getContentAsString()
            .replaceAll("\"", ""));
    }

    public TournamentResponseDto getTournamentByIdWithoutUuids(UUID id, boolean skipUuids) throws Exception {
        var jsonResponse = mockMvc.perform(
                get("/tournaments/{tournamentId}", id)
                    .contentType("application/json"))
            .andExpect(status().is(200))
            .andReturn()
            .getResponse()
            .getContentAsString();

        jsonResponse = MockMvcTools.applySkipUuidsFilter(jsonResponse, skipUuids);

        return objectMapper.readValue(jsonResponse, TournamentResponseDto.class);
    }

    public List<SearchTournamentsDto> getListOfTournamentsWithoutUuids(
        Long page, Long size, boolean skipUuids)
        throws Exception {
        var jsonResponse = mockMvc.perform(
                get("/tournaments")
                    .queryParam("page", page.toString())
                    .queryParam("size", size.toString())
                    .contentType("application/json"))
            .andExpect(status().is(200))
            .andReturn()
            .getResponse()
            .getContentAsString();

        jsonResponse = MockMvcTools.applySkipUuidsFilter(jsonResponse, skipUuids);
        return objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
    }


}
