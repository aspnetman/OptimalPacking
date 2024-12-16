package ru.liga;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.controller.ConsoleController;
import ru.liga.packages.importPackages.ImportPackagesCommandHandler;

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
