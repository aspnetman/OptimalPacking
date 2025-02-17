package ru.liga.optimalpacking.config;

import an.awesome.pipelinr.Pipeline;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import ru.liga.optimalpacking.billings.addbilling.AddBillingCommand;
import ru.liga.optimalpacking.billings.addbilling.dto.BillingDto;

import java.util.function.Consumer;

@Configuration
public class ConsumerConfig {

    @Bean
    public Consumer<Message<BillingDto>> addBilling(Pipeline pipeline) {
        return message -> new AddBillingCommand(message.getPayload()).execute(pipeline);
    }
}
