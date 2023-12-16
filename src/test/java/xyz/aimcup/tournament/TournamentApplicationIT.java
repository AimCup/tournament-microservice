package xyz.aimcup.tournament;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import xyz.aimcup.tournament.reusablecontainers.DatabaseContainerIT;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class TournamentApplicationIT extends DatabaseContainerIT {

    @Test
    @SuppressWarnings("squid:S2699")
    void contextLoads() {
    }

}
