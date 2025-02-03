package ru.liga.optimalpacking.packages.deleteparcel.middlewares;

import an.awesome.pipelinr.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.packages.deleteparcel.DeleteParcelCommand;

@Component
@Order(4)
@Slf4j
public class DeleteParcelLoggingMiddleware implements Command.Middleware {
    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {
        if (c instanceof DeleteParcelCommand) {
            log.info("Удаление посылки: {}", ((DeleteParcelCommand) c).name());

            var result = next.invoke();

            log.info("Посылка {} удалена", ((DeleteParcelCommand) c).name());

            return result;
        } else {
            return next.invoke();
        }

    }
}
