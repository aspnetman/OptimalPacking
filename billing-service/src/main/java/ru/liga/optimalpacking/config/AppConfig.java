package ru.liga.optimalpacking.config;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.liga.optimalpacking.billings.getbillingdetail.GetBillingDetailQueryHandler;
import ru.liga.optimalpacking.billings.shared.BillingRepository;

@Configuration
public class AppConfig {

    @Bean
    Pipeline pipeline(ObjectProvider<Command.Handler> commandHandlers,
                      ObjectProvider<Notification.Handler> notificationHandlers,
                      ObjectProvider<Command.Middleware> middlewares) {
        return new Pipelinr()
                .with(commandHandlers::stream)
                .with(notificationHandlers::stream)
                .with(middlewares::orderedStream);
    }

    @Bean
    public GetBillingDetailQueryHandler getBillingDetailQueryHandler(
            BillingRepository billingRepository) {
        return new GetBillingDetailQueryHandler(billingRepository);
    }
}
