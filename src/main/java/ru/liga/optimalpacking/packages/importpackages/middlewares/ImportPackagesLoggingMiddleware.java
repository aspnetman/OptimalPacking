package ru.liga.optimalpacking.packages.importpackages.middlewares;

import an.awesome.pipelinr.Command;
import lombok.extern.slf4j.Slf4j;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommand;
import ru.liga.optimalpacking.packages.importpackages.dto.ImportPackagesResponse;

@Slf4j
public class ImportPackagesLoggingMiddleware implements Command.Middleware {
    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {

        if (c instanceof ImportPackagesCommand) {
            log.info("Пустые клетки представлены символом ., а заполненные — символом X");

            var result = next.invoke();

            log.info("Необходимое количество машин: {}", ((ImportPackagesResponse) result).filledTrucks().size());

            return result;
        } else {
            return next.invoke();
        }
    }
}
