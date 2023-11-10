package xyz.aimcup.tournament.reusablecontainers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(DatabaseContainerIT.class)
@SpringBootTest
public abstract class TestContainersFullSetupIT {

}
