package ru.liga.packages.importPackages;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Comparator;
import ru.liga.packages.importPackages.dto.ImportPackagesResponse;
import ru.liga.packages.importPackages.entities.Parcel;
import ru.liga.packages.importPackages.entities.Truck;

@Slf4j
public class ImportPackagesCommandHandler {

    public ImportPackagesResponse handle(ImportPackagesCommand command) throws Exception {
        // Чтение данных из файла
        List<Parcel> parcels = FileParcer.readParcelsFromFile(command.getPathToFile());

        // Сортируем посылки по убыванию площади
        parcels.sort(Comparator.comparing((Parcel p) -> p.getWidth() * p.getHeight()).reversed());

        int totalMachinesNeeded = packParcels(parcels);

        return new ImportPackagesResponse(totalMachinesNeeded);
    }

    public int packParcels(List<Parcel> parcels) {

        if (parcels.isEmpty()){
            return 0;
        }

        List<Truck> filledTrucks = new ArrayList<>(); // Список заполненных грузовиков
        int machinesUsed = 1;
        Truck currentTruck = new Truck();

        for (Parcel parcel : parcels) {
            if (!currentTruck.tryToFitParcel(parcel)) {
                if (currentTruck.isEmpty()) {
                    continue; // Пропустить создание новой машины, если текущая машина пустая
                }
                machinesUsed++;
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

        if (filledTrucks.isEmpty()) {
            machinesUsed = 0;
        }

        return machinesUsed;
    }
}
