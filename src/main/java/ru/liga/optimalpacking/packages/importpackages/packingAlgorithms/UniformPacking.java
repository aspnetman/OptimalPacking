package ru.liga.optimalpacking.packages.importpackages.packingAlgorithms;

import ru.liga.optimalpacking.packages.shared.entities.Parcel;
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

        if (maxTrucks > 0) {
            int numOfTrucks = Math.min(parcels.size(), maxTrucks);

            for (int i = 0; i < numOfTrucks; i++) {
                trucks.add(new Truck());
            }

            int currentTruckIndex = 0;
            for (Parcel parcel : parcels) {
                boolean placed = false;
                for (int i = 0; i < numOfTrucks; i++) {
                    if (trucks.get((currentTruckIndex + i) % numOfTrucks).tryToFitParcel(parcel)) {
                        trucks.get((currentTruckIndex + i) % numOfTrucks).placeParcel(parcel);
                        placed = true;
                        break;
                    }
                }

                if (!placed) {
                    notPackedParcels.add(parcel);
                }

                currentTruckIndex = (currentTruckIndex + 1) % numOfTrucks;
            }
        } else {
            notPackedParcels.addAll(parcels);
        }

        return new PackingResult(trucks, notPackedParcels);
    }
}
