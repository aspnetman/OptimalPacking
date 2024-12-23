package ru.liga.optimalpacking.packages.shared.middlewares;

import an.awesome.pipelinr.Command;
import lombok.extern.slf4j.Slf4j;
import ru.liga.optimalpacking.packages.shared.exceptions.InvalidOperationException;

@Slf4j
public class ExceptionMiddleware implements Command.Middleware {
    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {

        R result = null;

        try {
            result = next.invoke();
        }
        catch (InvalidOperationException invalidOperationException) {
            log.error(invalidOperationException.getMessage(), invalidOperationException);
        }
        catch (Exception exception) {
            log.error("Error", exception);
        }

        return result;
    }
}
