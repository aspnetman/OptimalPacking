package ru.liga.optimalpacking.packages.getparcels.middlewares;

import an.awesome.pipelinr.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.packages.getparcels.GetParcelsQuery;
import ru.liga.optimalpacking.packages.getparcels.dto.GetParcelsResponse;

@Component
@Order(7)
@Slf4j
public class GetParcelsLoggingMiddleware implements Command.Middleware {
    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {
        if (c instanceof GetParcelsQuery) {
            log.info("Получение посылок");

            var result = next.invoke();

            log.info("Посылки: {}", ((GetParcelsResponse) result).parcels());

            return result;
        } else {
            return next.invoke();
        }
    }
}
