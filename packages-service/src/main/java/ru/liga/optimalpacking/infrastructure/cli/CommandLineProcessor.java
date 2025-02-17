package ru.liga.optimalpacking.infrastructure.cli;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import ru.liga.optimalpacking.infrastructure.controller.PackagesController;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommand;
import ru.liga.optimalpacking.packages.importpackages.dto.PackingAlgorithm;

@Slf4j
@RequiredArgsConstructor
public class CommandLineProcessor {

    private static final String EXIT_COMMAND = "exit";

    private final PackagesController packagesController;

    private final OptionsFactory optionsFactory;

    private final DefaultParser parser;

    private static void processArguments(CommandLine cmd, PackagesController consoleController, OptionsFactory optionsFactory) {
        if (cmd.hasOption("delete")) {
            consoleController.deleteParcel(cmd.getOptionValue("delete"));
        } else if (cmd.hasOption("edit")) {

            var width = Integer.parseInt(cmd.getOptionValue("width"));

            consoleController.editParcel(
                    cmd.getOptionValue("edit"),
                    cmd.getOptionValue("form"),
                    cmd.getOptionValue("symbol").charAt(0),
                    width,
                    Integer.parseInt(cmd.getOptionValue("height")),
                    cmd.getOptionValue("name"));
        } else if (cmd.hasOption("get")) {
            consoleController.getParcel(cmd.getOptionValue("get"));
        } else if (cmd.hasOption("list")) {
            consoleController.getParcels(
                    Integer.parseInt(cmd.getOptionValue("offset")),
                    Integer.parseInt(cmd.getOptionValue("limit"))
            );
        } else if (cmd.hasOption("height")) {
            printHelp(optionsFactory);
        } else if (cmd.hasOption("import")) {
            consoleController.importPackages(
                    new ImportPackagesCommand(
                    cmd.getOptionValue("userId"),
                    cmd.getOptionValue("file"),
                    Integer.parseInt(cmd.getOptionValue("maxTrucks")),
                    PackingAlgorithm.valueOf(cmd.getOptionValue("packingAlgorithm"))));
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
