package ru.liga.optimalpacking.packages.importpackages.packingAlgorithms;

import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;
import ru.liga.optimalpacking.packages.importpackages.entities.PackingResult;
import ru.liga.optimalpacking.packages.importpackages.entities.Truck;

import java.util.ArrayList;
import java.util.List;

public class UniformPacking {
    /**
     * Алгоритм равномерной загрузки
     * Целью этого алгоритма является распределение посылок между всеми доступными грузовиками таким образом, чтобы загрузка была примерно одинаковой для каждого грузовика.
     * Это полезно, когда важно минимизировать разницу в загруженности машин.
     *
     * @param parcels
     * @param maxTrucks
     * @return
     */
    public PackingResult pack(List<Parcel> parcels, Integer maxTrucks) {
        List<Truck> trucks = new ArrayList<>();
        List<Parcel> notPackedParcels = new ArrayList<>();
        int numOfTrucks = Math.min(parcels.size(), maxTrucks);

        for (int i = 0; i < numOfTrucks; i++) {
            trucks.add(new Truck());
        }

        int index = 0;
        for (Parcel parcel : parcels) {
            while (index < numOfTrucks && !trucks.get(index % numOfTrucks).tryToFitParcel(parcel)) {
                index++;
            }

            if (index >= numOfTrucks) {
                notPackedParcels.add(parcel);
                continue;
            }

            trucks.get(index % numOfTrucks).placeParcel(parcel);
            index++;
        }

        return new PackingResult(trucks, notPackedParcels);
    }
}
