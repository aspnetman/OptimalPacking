package ru.liga.optimalpacking;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.optimalpacking.controller.ConsoleController;
import ru.liga.optimalpacking.packages.exportpackages.ExportPackagesCommandHandler;
import ru.liga.optimalpacking.packages.exportpackages.ParcelsRepository;
import ru.liga.optimalpacking.packages.exportpackages.TrucksProvider;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommandHandler;
import ru.liga.optimalpacking.packages.importpackages.TrucksRepository;
import ru.liga.optimalpacking.packages.importpackages.businessRules.CheckFilledTrucksExceededMaxValueBusinessRule;
import ru.liga.optimalpacking.packages.importpackages.middlewares.ImportPackagesLoggingMiddleware;
import ru.liga.optimalpacking.packages.shared.middlewares.ExceptionMiddleware;

import java.util.stream.Stream;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Стартуем приложение...");
        App.start();
    }

    private static void start() {

        Pipeline pipeline = new Pipelinr()
                .with(
                        () -> Stream.of(new ImportPackagesCommandHandler(
                                new TrucksRepository(),
                                new ru.liga.optimalpacking.packages.importpackages.businessRules.BusinessRulesChecker(new CheckFilledTrucksExceededMaxValueBusinessRule())),
                                new ExportPackagesCommandHandler(
                                        new TrucksProvider(),
                                        new ParcelsRepository()))
                ).with(
                        () -> Stream.of(new ExceptionMiddleware(), new ImportPackagesLoggingMiddleware())
                );

        ConsoleController consoleController = new ConsoleController(pipeline);

        consoleController.listen();
    }
}
