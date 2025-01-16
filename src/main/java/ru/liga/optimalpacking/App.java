package ru.liga.optimalpacking;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.DefaultParser;
import ru.liga.optimalpacking.infrastructure.cli.CommandLineProcessor;
import ru.liga.optimalpacking.infrastructure.cli.ConsoleController;
import ru.liga.optimalpacking.infrastructure.cli.OptionsFactory;
import ru.liga.optimalpacking.infrastructure.controller.OptimalPackingController;
import ru.liga.optimalpacking.infrastructure.telegram.TelegramController;
import ru.liga.optimalpacking.packages.deleteparcel.DeleteParcelCommandHandler;
import ru.liga.optimalpacking.packages.deleteparcel.DeleteParcelsRepository;
import ru.liga.optimalpacking.packages.deleteparcel.businessrules.CheckParcelExistsBusinessRule;
import ru.liga.optimalpacking.packages.editparcel.EditParcelCommandHandler;
import ru.liga.optimalpacking.packages.editparcel.EditParcelMapperImpl;
import ru.liga.optimalpacking.packages.editparcel.EditParcelsRepository;
import ru.liga.optimalpacking.packages.exportpackages.ExportPackagesCommandHandler;
import ru.liga.optimalpacking.packages.exportpackages.ParcelsRepository;
import ru.liga.optimalpacking.packages.exportpackages.TrucksProvider;
import ru.liga.optimalpacking.packages.getparcel.GetParcelQueryHandler;
import ru.liga.optimalpacking.packages.getparcel.GetParcelRepository;
import ru.liga.optimalpacking.packages.getparcels.GetParcelsQueryHandler;
import ru.liga.optimalpacking.packages.getparcels.GetParcelsRepository;
import ru.liga.optimalpacking.packages.importpackages.FileParcelsReader;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommandHandler;
import ru.liga.optimalpacking.packages.importpackages.PackingService;
import ru.liga.optimalpacking.packages.importpackages.TrucksRepository;
import ru.liga.optimalpacking.packages.importpackages.businessrules.BusinessRulesChecker;
import ru.liga.optimalpacking.packages.importpackages.businessrules.CheckFilledTrucksExceededMaxValueBusinessRule;
import ru.liga.optimalpacking.packages.importpackages.middlewares.ImportPackagesLoggingMiddleware;
import ru.liga.optimalpacking.packages.importpackages.packingalgorithms.DensePacking;
import ru.liga.optimalpacking.packages.importpackages.packingalgorithms.UniformPacking;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;
import ru.liga.optimalpacking.packages.shared.middlewares.ExceptionMiddleware;
import ru.liga.optimalpacking.packages.shared.middlewares.NotFoundMiddleware;

import java.util.HashMap;
import java.util.stream.Stream;

@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("Стартуем приложение...");
        App.start();
    }

    private static void start() {

        FileParcelsReader fileParcelsReader = new FileParcelsReader();
        var initParcels = fileParcelsReader.readParcelsFromFile("src/main/resources/packages.txt")
                .stream()
                .collect(HashMap<String, Parcel>::new,
                        (map, parcel) -> map.put(parcel.name(), parcel),
                        HashMap::putAll);

        var sharedParcelsRepository = new ru.liga.optimalpacking.packages.shared.ParcelsRepository(initParcels);
        var deleteParcelsRepository = new DeleteParcelsRepository(sharedParcelsRepository);
        var editParcelsRepository = new EditParcelsRepository(sharedParcelsRepository);

        Pipeline pipeline = new Pipelinr()
                .with(
                        () -> Stream.of(new ImportPackagesCommandHandler(
                                        new TrucksRepository(),
                                        new BusinessRulesChecker(new CheckFilledTrucksExceededMaxValueBusinessRule()),
                                        new PackingService(new DensePacking(), new UniformPacking()),
                                        new FileParcelsReader()),
                                new ExportPackagesCommandHandler(
                                        new TrucksProvider(),
                                        new ParcelsRepository()),
                                new GetParcelQueryHandler(
                                        new GetParcelRepository(sharedParcelsRepository)),
                                new GetParcelsQueryHandler(
                                        new GetParcelsRepository(sharedParcelsRepository)),
                                new DeleteParcelCommandHandler(
                                        deleteParcelsRepository,
                                        new ru.liga.optimalpacking.packages.deleteparcel.businessrules.BusinessRulesChecker(
                                                new CheckParcelExistsBusinessRule(deleteParcelsRepository))),
                                new EditParcelCommandHandler(
                                        editParcelsRepository,
                                        new ru.liga.optimalpacking.packages.editparcel.businessrules.BusinessRulesChecker(
                                                new ru.liga.optimalpacking.packages.editparcel.businessrules.CheckParcelExistsBusinessRule(editParcelsRepository)),
                                        new EditParcelMapperImpl()))
                ).with(
                        () -> Stream.of(
                                new ExceptionMiddleware(),
                                new ImportPackagesLoggingMiddleware(),
                                new NotFoundMiddleware())
                );

        var commandLineProcessor = new CommandLineProcessor(
                new OptimalPackingController(pipeline),
                new OptionsFactory(),
                new DefaultParser());

        var consoleController = new ConsoleController(
                commandLineProcessor);

        var telegramController = new TelegramController(
                "7589989172:AAFzKmwuDHribOZ0V-uhwxNusIpSRmil5LA", // Перенести в конфиг файл
                "loadingparcels_tgbot", // Перенести в конфиг файл
                commandLineProcessor);

        consoleController.listen();
        telegramController.listen();
    }
}
