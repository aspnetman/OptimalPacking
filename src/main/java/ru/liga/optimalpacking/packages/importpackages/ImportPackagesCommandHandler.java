package ru.liga.optimalpacking.packages.importpackages;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import ru.liga.optimalpacking.packages.importpackages.businessRules.BusinessRulesChecker;
import ru.liga.optimalpacking.packages.importpackages.dto.ImportPackagesResponse;
import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;
import ru.liga.optimalpacking.packages.importpackages.entities.Truck;

@Slf4j
@RequiredArgsConstructor
public class ImportPackagesCommandHandler implements Command.Handler<ImportPackagesCommand, ImportPackagesResponse> {

    private final TrucksRepository trucksRepository;

    private final BusinessRulesChecker businessRulesChecker;

    @Override
    public ImportPackagesResponse handle(ImportPackagesCommand command) {

        // Сортируем посылки по убыванию площади
        command.parcels().sort(Comparator.comparing((Parcel p) -> p.getWidth() * p.getHeight()).reversed());

        var filledTrucks = packParcels(command.parcels());

        businessRulesChecker.checkFilledTrucksExceededMaxValue(filledTrucks, command.maxTrucks());

        trucksRepository.saveResultsToJSON(filledTrucks, "results.json");

        return new ImportPackagesResponse(filledTrucks);
    }

    private List<Truck> packParcels(List<Parcel> parcels) {

        List<Truck> filledTrucks = new ArrayList<>(); // Список заполненных грузовиков

        if (parcels.isEmpty()){
            return filledTrucks;
        }

        Truck currentTruck = new Truck();

        for (Parcel parcel : parcels) {
            if (!currentTruck.tryToFitParcel(parcel)) {
                if (currentTruck.isEmpty()) {
                    continue; // Пропустить создание новой машины, если текущая машина пустая
                }

                currentTruck = new Truck();
            }
            if (currentTruck.tryToFitParcel(parcel)) {
                currentTruck.placeParcel(parcel);
                currentTruck.printGrid();
            }

            if (!currentTruck.isEmpty()) {
                filledTrucks.add(currentTruck);
            }
        }

        return filledTrucks;
    }
}
