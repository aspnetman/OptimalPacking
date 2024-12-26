package ru.liga.optimalpacking;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import lombok.extern.slf4j.Slf4j;
import ru.liga.optimalpacking.controller.ConsoleController;
import ru.liga.optimalpacking.packages.exportpackages.ExportPackagesCommandHandler;
import ru.liga.optimalpacking.packages.exportpackages.ParcelsRepository;
import ru.liga.optimalpacking.packages.exportpackages.TrucksProvider;
import ru.liga.optimalpacking.packages.importpackages.FileParser;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommandHandler;
import ru.liga.optimalpacking.packages.importpackages.TrucksRepository;
import ru.liga.optimalpacking.packages.importpackages.businessRules.BusinessRulesChecker;
import ru.liga.optimalpacking.packages.importpackages.businessRules.CheckFilledTrucksExceededMaxValueBusinessRule;
import ru.liga.optimalpacking.packages.importpackages.middlewares.ImportPackagesLoggingMiddleware;
import ru.liga.optimalpacking.packages.shared.middlewares.ExceptionMiddleware;

import java.util.stream.Stream;

@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("Стартуем приложение...");
        App.start();
    }

    private static void start() {

        Pipeline pipeline = new Pipelinr()
                .with(
                        () -> Stream.of(new ImportPackagesCommandHandler(
                                        new TrucksRepository(),
                                        new BusinessRulesChecker(new CheckFilledTrucksExceededMaxValueBusinessRule())),
                                new ExportPackagesCommandHandler(
                                        new TrucksProvider(),
                                        new ParcelsRepository()))
                ).with(
                        () -> Stream.of(new ExceptionMiddleware(), new ImportPackagesLoggingMiddleware())
                );

        ConsoleController consoleController = new ConsoleController(pipeline, new FileParser());

        consoleController.listen();
    }
}
