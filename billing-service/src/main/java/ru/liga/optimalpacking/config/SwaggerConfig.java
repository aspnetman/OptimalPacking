package ru.liga.optimalpacking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Конфигурация Swagger
 */
@Configuration
public class SwaggerConfig {

    @Value("${TAG:undefined}")
    private String appVersion;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(new Server().url("/")))
                .info(new Info()
                        .title("Parcels API")
                        .version(appVersion)
                        .description("Обучение"));
    }
}