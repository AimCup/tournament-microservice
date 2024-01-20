package xyz.aimcup.tournament.reusablecontainers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import xyz.aimcup.tournament.data.repository.tournament.TournamentRepository;

@ExtendWith(DatabaseContainerIT.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class TestContainersFullSetupIT {

    @Autowired
    private TournamentRepository tournamentRepository;

    public void cleanTournamentRepository() {
        tournamentRepository.deleteAll();
    }
}
