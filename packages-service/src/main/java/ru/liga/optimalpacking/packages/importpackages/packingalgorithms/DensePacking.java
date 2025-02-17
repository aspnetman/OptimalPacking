package ru.liga.optimalpacking.packages.importpackages.packingalgorithms;

import ru.liga.optimalpacking.packages.shared.entities.Parcel;
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

        if (parcels.isEmpty()) {
            return new PackingResult(trucks, notPackedParcels); // Возвращаем пустой результат, если список посылок пуст
        }

        Truck currentTruck = new Truck();
        trucks.add(currentTruck);

        for (Parcel parcel : parcels) {
            if (currentTruck.tryToFitParcel(parcel)) {
                currentTruck.placeParcel(parcel); // Посылка помещается в текущий грузовик
            } else {
                // Пытаемся создать новый грузовик и разместить посылку в нем
                Truck newTruck = new Truck();
                if (newTruck.tryToFitParcel(parcel)) {
                    newTruck.placeParcel(parcel);
                    trucks.add(newTruck);
                    currentTruck = newTruck; // Переключаемся на новый грузовик
                } else {
                    // Посылка не помещается ни в один грузовик
                    notPackedParcels.add(parcel);
                }
            }
        }

        return new PackingResult(trucks, notPackedParcels);
    }
}
