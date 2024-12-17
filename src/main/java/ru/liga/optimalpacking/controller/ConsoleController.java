package ru.liga.optimalpacking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommand;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommandHandler;

import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {

    private final ImportPackagesCommandHandler importPackagesCommandHandler;

    public void listen() throws Exception {
        var scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                System.exit(0);
            }

            log.info("Пустые клетки представлены символом ., а заполненные — символом X");

            try {
                var response = this.importPackagesCommandHandler.handle(new ImportPackagesCommand(command));

                log.info("Необходимое количество машин: {}", response.getTotalMachinesNeeded());
            }
            catch (Exception exception) {
                log.error("Error", exception);
            }
        }
    }
}

