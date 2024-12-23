package ru.liga.optimalpacking.controller;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.repack.org.checkerframework.checker.units.qual.N;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommand;
import ru.liga.optimalpacking.packages.importpackages.FileParcer;

import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {

    private final Pipeline pipeline;

    public void listen() {
        try {
            var scanner = new Scanner(System.in);

            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                if (command.equals("exit")) {
                    System.exit(0);
                }

                pipeline.send(new ImportPackagesCommand(FileParcer.readParcelsFromFile(command), null));
            }
        } catch (Exception exception) {
            log.error("Error", exception);
        }
    }
}


