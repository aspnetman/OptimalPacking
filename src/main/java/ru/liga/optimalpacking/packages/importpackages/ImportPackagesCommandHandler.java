package ru.liga.optimalpacking.packages.importpackages;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.optimalpacking.packages.importpackages.businessRules.BusinessRulesChecker;
import ru.liga.optimalpacking.packages.importpackages.dto.ImportPackagesResponse;
import ru.liga.optimalpacking.packages.importpackages.dto.PackingAlgorithm;
import ru.liga.optimalpacking.packages.importpackages.entities.PackingResult;

@Slf4j
@RequiredArgsConstructor
public class ImportPackagesCommandHandler implements Command.Handler<ImportPackagesCommand, ImportPackagesResponse> {

    private final TrucksRepository trucksRepository;

    private final BusinessRulesChecker businessRulesChecker;

    private final PackingService packingService;

    private final FileParser fileParser;

    @Override
    public ImportPackagesResponse handle(ImportPackagesCommand command) {

        var parcels = fileParser.readParcelsFromFile(command.file());

        var packingResult = packingService.pack(
                fileParser.readParcelsFromFile(command.file()),
                command.maxTrucks(),
                command.packingAlgorithm());

        businessRulesChecker.checkFilledTrucksExceededMaxValue(packingResult.notPackedParcels());

        trucksRepository.saveResultsToJson(packingResult.trucks(), "results.json");

        return new ImportPackagesResponse(packingResult.trucks());
    }
}