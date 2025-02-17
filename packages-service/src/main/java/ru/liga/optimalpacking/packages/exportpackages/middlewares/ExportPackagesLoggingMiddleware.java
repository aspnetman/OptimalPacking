package ru.liga.optimalpacking.packages.exportpackages.middlewares;

import an.awesome.pipelinr.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.packages.exportpackages.ExportPackagesCommand;
import ru.liga.optimalpacking.packages.exportpackages.dto.ExportPackagesResponse;

@Component
@Order(3)
@Slf4j
public class ExportPackagesLoggingMiddleware implements Command.Middleware {
    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {
        if (c instanceof ExportPackagesCommand) {
            log.info("Старт выгрузки посылок");

            var result = next.invoke();

            log.info("Посылки выгружены в: {}", ((ExportPackagesResponse) result).fileName());

            return result;
        } else {
            return next.invoke();
        }
    }
}
