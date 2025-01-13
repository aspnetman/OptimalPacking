package ru.liga.optimalpacking;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import ru.liga.optimalpacking.controller.ConsoleController;
import ru.liga.optimalpacking.packages.deleteparcel.DeleteParcelCommandHandler;
import ru.liga.optimalpacking.packages.deleteparcel.businessRules.CheckParcelExistsBusinessRule;
import ru.liga.optimalpacking.packages.editparcel.EditParcelCommandHandler;
import ru.liga.optimalpacking.packages.editparcel.EditParcelMapperImpl;
import ru.liga.optimalpacking.packages.exportpackages.ExportPackagesCommandHandler;
import ru.liga.optimalpacking.packages.exportpackages.ParcelsRepository;
import ru.liga.optimalpacking.packages.exportpackages.TrucksProvider;
import ru.liga.optimalpacking.packages.getparcel.GetParcelQueryHandler;
import ru.liga.optimalpacking.packages.getparcels.GetParcelsQueryHandler;
import ru.liga.optimalpacking.packages.importpackages.FileParser;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommandHandler;
import ru.liga.optimalpacking.packages.importpackages.PackingService;
import ru.liga.optimalpacking.packages.importpackages.TrucksRepository;
import ru.liga.optimalpacking.packages.importpackages.businessRules.BusinessRulesChecker;
import ru.liga.optimalpacking.packages.importpackages.businessRules.CheckFilledTrucksExceededMaxValueBusinessRule;
import ru.liga.optimalpacking.packages.importpackages.dto.PackingAlgorithm;
import ru.liga.optimalpacking.packages.importpackages.middlewares.ImportPackagesLoggingMiddleware;
import ru.liga.optimalpacking.packages.importpackages.packingAlgorithms.DensePacking;
import ru.liga.optimalpacking.packages.importpackages.packingAlgorithms.UniformPacking;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;
import ru.liga.optimalpacking.packages.shared.middlewares.ExceptionMiddleware;
import ru.liga.optimalpacking.packages.shared.middlewares.NotFoundMiddleware;

import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

@Slf4j
public class App {

    private static final String ExitCommand = "exit";

    public static void main(String[] args) {
        log.info("Стартуем приложение...");
        App.start();
    }

    private static void start() {

        FileParser fileParser = new FileParser();
        var initParcels = fileParser.readParcelsFromFile("src/main/resources/packages.txt")
                .stream()
                .collect(HashMap<String, Parcel>::new,
                        (map, parcel) -> map.put(parcel.name(), parcel),
                        HashMap::putAll);

        var sharedParcelsRepository = new ru.liga.optimalpacking.packages.shared.ParcelsRepository(initParcels);
        var deleteParcelsRepository = new ru.liga.optimalpacking.packages.deleteparcel.ParcelsRepository(sharedParcelsRepository);
        var editParcelsRepository = new ru.liga.optimalpacking.packages.editparcel.ParcelsRepository(sharedParcelsRepository);

        Pipeline pipeline = new Pipelinr()
                .with(
                        () -> Stream.of(new ImportPackagesCommandHandler(
                                        new TrucksRepository(),
                                        new BusinessRulesChecker(new CheckFilledTrucksExceededMaxValueBusinessRule()),
                                        new PackingService(new DensePacking(), new UniformPacking()),
                                        new FileParser()),
                                new ExportPackagesCommandHandler(
                                        new TrucksProvider(),
                                        new ParcelsRepository()),
                                new GetParcelQueryHandler(
                                        new ru.liga.optimalpacking.packages.getparcel.ParcelsRepository(sharedParcelsRepository)),
                                new GetParcelsQueryHandler(
                                        new ru.liga.optimalpacking.packages.getparcels.ParcelsRepository(sharedParcelsRepository)),
                                new DeleteParcelCommandHandler(
                                        deleteParcelsRepository,
                                        new ru.liga.optimalpacking.packages.deleteparcel.businessRules.BusinessRulesChecker(
                                                new CheckParcelExistsBusinessRule(deleteParcelsRepository))),
                                new EditParcelCommandHandler(
                                        editParcelsRepository,
                                        new ru.liga.optimalpacking.packages.editparcel.businessRules.BusinessRulesChecker(
                                                new ru.liga.optimalpacking.packages.editparcel.businessRules.CheckParcelExistsBusinessRule(editParcelsRepository)),
                                        new EditParcelMapperImpl()))
                ).with(
                        () -> Stream.of(
                                new ExceptionMiddleware(),
                                new ImportPackagesLoggingMiddleware(),
                                new NotFoundMiddleware())
                );

        var parser = createParser();
        var options = createOptions();
        var consoleController = new ConsoleController(pipeline);

        var scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            try {
                String command = scanner.nextLine();
                if (command.equals(ExitCommand)) {
                    System.exit(0);
                }

                processArguments(parser.parse(options, command.split("\\s+")), consoleController);
            } catch (ParseException e) {
                printHelp();
            } catch (Exception ignored) {
            }
        }
    }

    private static CommandLineParser createParser() {
        return new DefaultParser();
    }

    private static Options createOptions() {
        var options = new Options();

        // Удаление посылки
        var deleteOption = new Option("d", "delete", true, "Удаление посылки");
        options.addOption(deleteOption);

        // Редактирование посылки
        var editOption = new Option("e", "edit", true, "Редактирование посылки");
        options.addOption(editOption);

        // Опция для указания имени посылки
        var nameOption = new Option("n", "name", true, "Указание названия посылки");
        options.addOption(nameOption);

        // Опция для указания ширины посылки
        var widthOption = new Option("w", "width", true, "Указание ширины посылки");
        options.addOption(widthOption);

        // Опция для указания высоты посылки
        var heightOption = new Option("h", "height", true, "Указание высоты посылки");
        options.addOption(heightOption);

        // Опция для получения посылки по имени
        var getOption = new Option("g", "get", true, "Получение посылки по имени");
        options.addOption(getOption);

        // Опция для получения списка посылок
        var listOption = new Option("l", "list", false, "Получение списка посылок");
        options.addOption(listOption);

        // Опция для импорта посылок
        var importOption = new Option("i", "import", true, "Импорт посылок");
        options.addOption(importOption);

        // Опция для указания файла для импорта
        var importFileOption = new Option("f", "file", true, "Указание файла для импорта");
        options.addOption(importFileOption);

        // Опция для указания максимального количества грузовиков
        var maxTrucksOption = new Option("m", "maxTrucks", true, "Указание максимального количества грузовиков");
        options.addOption(maxTrucksOption);

        // Опция для указания алгоритма
        var packingAlgorithmOption = new Option("a", "packingAlgorithm", true, "Указание алгоритма");
        options.addOption(packingAlgorithmOption);

        // Опция для получения помощи
        var helpOption = new Option("s", "help", false, "Получение помощи");
        options.addOption(helpOption);

        return options;
    }

    private static void processArguments(CommandLine cmd, ConsoleController consoleController) {
        if (cmd.hasOption('d')) {
            consoleController.deleteParcel(cmd.getOptionValue('d'));
        } else if (cmd.hasOption('e')) {

            var w = Integer.parseInt(cmd.getOptionValue('w'));

            consoleController.editParcel(
                    cmd.getOptionValue('e'),
                    new ru.liga.optimalpacking.packages.editparcel.dto.Parcel(
                            w,
                            Integer.parseInt(cmd.getOptionValue('h')),
                            cmd.getOptionValue('n')));
        } else if (cmd.hasOption('g')) {
            consoleController.getParcel(cmd.getOptionValue('g'));
        } else if (cmd.hasOption('l')) {
            consoleController.getParcels();
        } else if (cmd.hasOption('h')) {
            printHelp();
        } else if (cmd.hasOption('i')) {
            consoleController.importPackages(
                    cmd.getOptionValue('f'),
                    Integer.parseInt(cmd.getOptionValue('m')),
                    PackingAlgorithm.valueOf(cmd.getOptionValue('a')));
        }
    }

    private static void printHelp() {
        var formatter = HelpFormatter.builder().get();
        formatter.printHelp("Синтаксис командной строки", createOptions());
    }
}
