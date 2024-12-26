package ru.liga.optimalpacking.packages.importpackages.packingAlgorithms;

import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;
import ru.liga.optimalpacking.packages.importpackages.entities.PackingResult;
import ru.liga.optimalpacking.packages.importpackages.entities.Truck;

import java.util.ArrayList;
import java.util.List;

public class DensePacking {
    /**
     * Алгоритм максимальной плотности
     * Этот алгоритм стремится упаковать максимальное количество посылок в минимальное число грузовиков.
     * Он полезен, когда важно минимизировать общее количество используемых грузовиков.
     *
     * @param parcels
     * @return
     */
    public PackingResult pack(List<Parcel> parcels) {
        List<Truck> trucks = new ArrayList<>();
        List<Parcel> notPackedParcels = new ArrayList<>();
        Truck currentTruck = new Truck();
        trucks.add(currentTruck);

        for (Parcel parcel : parcels) {
            if (!currentTruck.tryToFitParcel(parcel)) {
                currentTruck = new Truck();
                trucks.add(currentTruck);
            }
            if (currentTruck.tryToFitParcel(parcel)) {
                currentTruck.placeParcel(parcel);
            } else {
                notPackedParcels.add(parcel);
            }
        }

        return new PackingResult(trucks, notPackedParcels);
    }
}
