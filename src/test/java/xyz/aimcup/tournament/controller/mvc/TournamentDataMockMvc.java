package xyz.aimcup.tournament.controller.mvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;

@Component
@ActiveProfiles("test")
public class TournamentDataMockMvc {

    @Autowired
    private MockMvc mockMvc;

    public UUID updateTournamentData(UUID tournamentId, String requestPath) throws Exception {
        return UUID.fromString(
            mockMvc.perform(patch("/tournaments/{tournamentId}/data", tournamentId)
                    .contentType("application/json")
                    .content(FileUtils.readFileToString(
                        ResourceUtils.getFile(requestPath),
                        StandardCharsets.UTF_8)))
                .andExpect(status().is(200))
                .andReturn()
                .getResponse()
                .getContentAsString()
                .replaceAll("\"", ""));
    }

}
