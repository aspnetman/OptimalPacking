package ru.liga.optimalpacking.packages.getparcel.middlewares;

import an.awesome.pipelinr.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.packages.getparcel.GetParcelQuery;
import ru.liga.optimalpacking.packages.getparcel.dto.GetParcelResponse;

@Component
@Order(6)
@Slf4j
public class GetParcelLoggingMiddleware implements Command.Middleware {
    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {
        if (c instanceof GetParcelQuery) {
            log.info("Получение посылки: {}", ((GetParcelQuery) c).packageName());

            var result = next.invoke();

            log.info("Посылка: {}", ((GetParcelResponse) result).getParcel());

            return result;
        } else {
            return next.invoke();
        }
    }
}
