package ru.liga.optimalpacking.packages.editparcel.middlewares;

import an.awesome.pipelinr.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.packages.editparcel.EditParcelCommand;

@Component
@Order(5)
@Slf4j
public class EditParcelLoggingMiddleware implements Command.Middleware {
    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {
        if (c instanceof EditParcelCommand) {
            log.info("Редактирование посылки: {}", ((EditParcelCommand) c).name());

            var result = next.invoke();

            log.info("Посылка {} отредактирована", ((EditParcelCommand) c).name());

            return result;
        } else {
            return next.invoke();
        }

    }
}
