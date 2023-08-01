package xyz.aimcup.tournament;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.aimcup.tournament.reusablecontainers.DatabaseContainerIT;

@SpringBootTest
class TournamentApplicationIT extends DatabaseContainerIT {

    @Test
    @SuppressWarnings("squid:S2699")
    void contextLoads() {
    }

}
