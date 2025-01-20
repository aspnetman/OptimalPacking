package ru.liga.optimalpacking.packages.exportpackages;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.packages.exportpackages.dto.ExportPackagesResponse;

/**
 * Принимает загруженные машины и отдаёт на выход файл со списком посылок
  */

@Component
@RequiredArgsConstructor
public class ExportPackagesCommandHandler implements Command.Handler<ExportPackagesCommand, ExportPackagesResponse> {

    private static final String fileName = "parcels.json";

    private final TrucksProvider trucksProvider;

    private final ExportPackagesParcelsRepository exportPackagesParcelsRepository;

    @Override
    public ExportPackagesResponse handle(ExportPackagesCommand exportPackagesCommand) {

        exportPackagesParcelsRepository.writeParcelsFromTrucksToFile(
                trucksProvider.getTrucksFromJson(exportPackagesCommand.trucksJson()),
                fileName);

        return new ExportPackagesResponse(fileName);
    }
}