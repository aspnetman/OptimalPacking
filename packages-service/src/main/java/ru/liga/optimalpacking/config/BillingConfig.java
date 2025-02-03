package ru.liga.optimalpacking.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "billing")
public class BillingConfig {
    private BigDecimal loadingCostPerSegment;
    private BigDecimal unloadingCostPerSegment;
}
