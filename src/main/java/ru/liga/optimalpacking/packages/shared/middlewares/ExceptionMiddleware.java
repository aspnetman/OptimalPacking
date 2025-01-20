package ru.liga.optimalpacking.packages.shared.middlewares;

import an.awesome.pipelinr.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Slf4j
public class ExceptionMiddleware implements Command.Middleware {
    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {

        R result = null;

        try {
            result = next.invoke();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }

        return result;
    }
}
