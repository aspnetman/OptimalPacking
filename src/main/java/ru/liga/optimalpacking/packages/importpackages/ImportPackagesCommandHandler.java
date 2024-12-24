package ru.liga.optimalpacking.packages.importpackages;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;

import ru.liga.optimalpacking.packages.importpackages.businessRules.BusinessRulesChecker;
import ru.liga.optimalpacking.packages.importpackages.dto.ImportPackagesResponse;
import ru.liga.optimalpacking.packages.importpackages.dto.PackingAlgorithm;
import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;
import ru.liga.optimalpacking.packages.importpackages.entities.PackingResult;

@Slf4j
@RequiredArgsConstructor
public class ImportPackagesCommandHandler implements Command.Handler<ImportPackagesCommand, ImportPackagesResponse> {

    private final TrucksRepository trucksRepository;

    private final BusinessRulesChecker businessRulesChecker;

    @Override
    public ImportPackagesResponse handle(ImportPackagesCommand command) {

        PackingResult packingResult;

        if (command.packingAlgorithm() == PackingAlgorithm.DensePacking) {
            packingResult = PackingAlgorithms.densePacking(command.parcels());
        }
        else {
            packingResult = PackingAlgorithms.uniformPacking(command.parcels(), command.maxTrucks());
        }

        businessRulesChecker.checkFilledTrucksExceededMaxValue(packingResult.notPackedParcels());

        trucksRepository.saveResultsToJSON(packingResult.trucks(), "results.json");

        return new ImportPackagesResponse(packingResult.trucks());
    }
}