package ru.liga.optimalpacking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.optimalpacking.controller.ConsoleController;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommandHandler;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        logger.info("Стартуем приложение...");
        App.start();
    }

    private static void start() throws Exception {
        ConsoleController consoleController = new ConsoleController(
                new ImportPackagesCommandHandler());
        consoleController.listen();
    }
}