package ru.liga.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.packages.importPackages.ImportPackagesCommand;
import ru.liga.packages.importPackages.ImportPackagesCommandHandler;

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

            var response = this.importPackagesCommandHandler.handle(new ImportPackagesCommand(command));

            log.info("Необходимое количество машин: {}", response.getTotalMachinesNeeded());
        }
    }
}

