package ru.liga.optimalpacking.packages.importpackages;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.packages.importpackages.businessrules.ImportPackagesBusinessRulesChecker;
import ru.liga.optimalpacking.packages.importpackages.dto.ImportPackagesResponse;

@Component
@Slf4j
@RequiredArgsConstructor
public class ImportPackagesCommandHandler implements Command.Handler<ImportPackagesCommand, ImportPackagesResponse> {

    private final TrucksRepository trucksRepository;

    private final ImportPackagesBusinessRulesChecker importPackagesBusinessRulesChecker;

    private final PackingService packingService;

    private final FileParcelsReader fileParcelsReader;

    private final ImportPackagesBillingService importPackagesBillingService;

    @Override
    public ImportPackagesResponse handle(ImportPackagesCommand command) {

        var packingResult = packingService.pack(
                fileParcelsReader.readParcelsFromFile(command.file()),
                command.maxTrucks(),
                command.packingAlgorithm());

        importPackagesBusinessRulesChecker.checkFilledTrucksExceededMaxValue(packingResult.notPackedParcels());

        importPackagesBillingService.addBillingForImportedPackages(command.userId(), packingResult.trucks());

        trucksRepository.saveResultsToJson(packingResult.trucks(), "results.json");

        return new ImportPackagesResponse(packingResult.trucks());
    }
}