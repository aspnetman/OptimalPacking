package ru.liga.optimalpacking.packages.exportpackages;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.packages.exportpackages.dto.ExportPackagesResponse;
import ru.liga.optimalpacking.packages.exportpackages.entities.Truck;
import ru.liga.optimalpacking.shared.BillingService;

/**
 * Принимает загруженные машины и отдаёт на выход файл со списком посылок
  */

@Component
@RequiredArgsConstructor
public class ExportPackagesCommandHandler implements Command.Handler<ExportPackagesCommand, ExportPackagesResponse> {

    private static final String fileName = "parcels.json";

    private final TrucksProvider trucksProvider;

    private final ExportPackagesParcelsRepository exportPackagesParcelsRepository;

    private final BillingService billingService;

    @Override
    public ExportPackagesResponse handle(ExportPackagesCommand exportPackagesCommand) {

        var trucks = trucksProvider.getTrucksFromFile(exportPackagesCommand.trucksFile());

        exportPackagesParcelsRepository.writeParcelsFromTrucksToFile(
                trucks,
                fileName);

        billingService.addBilling(exportPackagesCommand.userId(),
                trucks.stream().mapToInt(Truck::occupiedSegmentsCount).sum(),
                "разгрузка");

        return new ExportPackagesResponse(fileName);
    }
}