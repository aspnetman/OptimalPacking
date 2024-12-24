package ru.liga.optimalpacking.packages.exportpackages;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.exportpackages.dto.ExportPackagesResponse;

// Принимает загруженные машины и отдаёт на выход файл со списком посылок
@RequiredArgsConstructor
public class ExportPackagesCommandHandler implements Command.Handler<ExportPackagesCommand, ExportPackagesResponse> {

    private final TrucksProvider trucksProvider;

    private final ParcelsRepository parcelsRepository;

    @Override
    public ExportPackagesResponse handle(ExportPackagesCommand exportPackagesCommand) {

        String fileName = "parcels.json";

        parcelsRepository.writeParcelsFromTrucksToFile(
                trucksProvider.getTrucksFromJson(exportPackagesCommand.trucksJson()),
                fileName);

        return new ExportPackagesResponse(fileName);
    }
}