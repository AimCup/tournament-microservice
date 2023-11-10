package xyz.aimcup.tournament.reusablecontainers;

import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class DatabaseContainerIT implements InvocationInterceptor {

    private static final String POSTGRES_VERSION = "postgres:latest";
    private static final PostgreSQLContainer<?> postgreSQLContainer;

    static {
        postgreSQLContainer =
            new PostgreSQLContainer<>(DockerImageName.parse(POSTGRES_VERSION))
                .withDatabaseName("test-db")
                .withUsername("test-username")
                .withPassword("test-password");

        postgreSQLContainer.start();
        setDatasourceConfig();
    }

    static void setDatasourceConfig() {
        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
    }
}
