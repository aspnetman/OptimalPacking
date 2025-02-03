package ru.liga.optimalpacking.infrastructure.cli;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {

    private final CommandLineProcessor commandLineProcessor;

    /**
     * Слушает команды из консоли
     */
    public void listen() {
        var scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            commandLineProcessor.processCommand(scanner.nextLine());
        }
    }
}
