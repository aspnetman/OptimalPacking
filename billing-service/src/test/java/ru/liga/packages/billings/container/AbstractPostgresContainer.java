package ru.liga.packages.billings.container;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(properties = "spring.profiles.active=test")
public abstract class AbstractPostgresContainer {

    final static String POSTGRES_VERSION = "postgres:latest";
    final static String DATABASE_NAME = "optimalpacking-billing";
    final static String DATABASE_USER = "optimalpacking-billing-user";
    final static String DATABASE_PASSWORD = "123456";

    @Container
    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(POSTGRES_VERSION)
            .withDatabaseName(DATABASE_NAME)
            .withUsername(DATABASE_USER)
            .withPassword(DATABASE_PASSWORD);

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        String jdbcUrl = "jdbc:postgresql://"
                + postgresContainer.getHost()
                + ":" + postgresContainer.getMappedPort(5432)
                + "/" + postgresContainer.getDatabaseName();

        registry.add("spring.datasource.url", () -> jdbcUrl);
    }
}