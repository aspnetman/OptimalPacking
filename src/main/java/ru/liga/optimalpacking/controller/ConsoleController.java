package ru.liga.optimalpacking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommand;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommandHandler;
import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;
import ru.liga.optimalpacking.shared.FileParcer;

import java.util.List;
import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {

    private final ImportPackagesCommandHandler importPackagesCommandHandler;

    public void listen() {
        try {
            var scanner = new Scanner(System.in);

            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                if (command.equals("exit")) {
                    System.exit(0);
                }

                log.info("Пустые клетки представлены символом ., а заполненные — символом X");

                var response = importPackagesCommandHandler.handle(new ImportPackagesCommand(FileParcer.readParcelsFromFile(command)));

                log.info("Необходимое количество машин: {}", response.getTotalMachinesNeeded());
            }
        } catch (Exception exception) {
            log.error("Error", exception);
        }
    }
}


