package ru.liga.optimalpacking.infrastructure.cli;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import ru.liga.optimalpacking.infrastructure.controller.PackagesController;
import ru.liga.optimalpacking.packages.importpackages.dto.PackingAlgorithm;

@Slf4j
@RequiredArgsConstructor
public class CommandLineProcessor {

    private static final String EXIT_COMMAND = "exit";

    private final PackagesController packagesController;

    private final OptionsFactory optionsFactory;

    private final DefaultParser parser;

    private static void processArguments(CommandLine cmd, PackagesController consoleController, OptionsFactory optionsFactory) {
        if (cmd.hasOption('d')) {
            consoleController.deleteParcel(cmd.getOptionValue('d'));
        } else if (cmd.hasOption('e')) {

            var w = Integer.parseInt(cmd.getOptionValue('w'));

            consoleController.editParcel(
                    cmd.getOptionValue('e'),
                    w,
                    Integer.parseInt(cmd.getOptionValue('h')),
                    cmd.getOptionValue('n'));
        } else if (cmd.hasOption('g')) {
            consoleController.getParcel(cmd.getOptionValue('g'));
        } else if (cmd.hasOption('l')) {
            consoleController.getParcels();
        } else if (cmd.hasOption('h')) {
            printHelp(optionsFactory);
        } else if (cmd.hasOption('i')) {
            consoleController.importPackages(
                    cmd.getOptionValue('u'),
                    cmd.getOptionValue('f'),
                    Integer.parseInt(cmd.getOptionValue('m')),
                    PackingAlgorithm.valueOf(cmd.getOptionValue('a')));
        }
    }

    private static void printHelp(OptionsFactory optionsFactory) {
        var formatter = HelpFormatter.builder().get();
        formatter.printHelp("Синтаксис командной строки", optionsFactory.createOptions());
    }

    public void processCommand(String command) {
        try {
            if (command.equals(EXIT_COMMAND)) {
                System.exit(0);
            }

            processArguments(parser.parse(optionsFactory.createOptions(), command.split("\\s+")),
                    packagesController,
                    optionsFactory);
        } catch (
                ParseException e) {
            printHelp(optionsFactory);
            log.error(e.getMessage(), e);
        } catch (Exception ignored) {
            log.error(ignored.getMessage(), ignored);
        }
    }
}
