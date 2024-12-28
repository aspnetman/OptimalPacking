package ru.liga.optimalpacking;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import lombok.extern.slf4j.Slf4j;
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
import ru.liga.optimalpacking.packages.importpackages.middlewares.ImportPackagesLoggingMiddleware;
import ru.liga.optimalpacking.packages.importpackages.packingAlgorithms.DensePacking;
import ru.liga.optimalpacking.packages.importpackages.packingAlgorithms.UniformPacking;
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

        var parcels = new HashMap<String, Parcel>();
        parcels.put("Штанга", new Parcel(3, 2, "Штанга"));

        var sharedParcelsRepository = new ru.liga.optimalpacking.packages.shared.ParcelsRepository(parcels);
        var deleteParcelsRepository = new ru.liga.optimalpacking.packages.deleteparcel.ParcelsRepository(sharedParcelsRepository);
        var editParcelsRepository = new ru.liga.optimalpacking.packages.editparcel.ParcelsRepository(sharedParcelsRepository);

        Pipeline pipeline = new Pipelinr()
                .with(
                        () -> Stream.of(new ImportPackagesCommandHandler(
                                        new TrucksRepository(),
                                        new BusinessRulesChecker(new CheckFilledTrucksExceededMaxValueBusinessRule()),
                                        new PackingService(new DensePacking(), new UniformPacking())),
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

        ConsoleController consoleController = new ConsoleController(pipeline, new FileParser());

        consoleController.listen();
    }
}
