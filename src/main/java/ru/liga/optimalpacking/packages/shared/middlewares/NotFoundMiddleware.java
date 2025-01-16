package ru.liga.optimalpacking.packages.shared.middlewares;

import an.awesome.pipelinr.Command;
import ru.liga.optimalpacking.packages.shared.exceptions.NotFoundException;

public class NotFoundMiddleware implements Command.Middleware {
    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {

        var result = next.invoke();

        if (!(c instanceof IRequestNotFound)) {
            return result;
        }

        if (result == null) {
            throw new NotFoundException(((IRequestNotFound) c).GetNotFoundErrorMessage());
        }

        return result;
    }
}
