package ru.liga.optimalpacking.packages.importpackages.packingAlgorithms;

import ru.liga.optimalpacking.packages.shared.entities.Parcel;
import ru.liga.optimalpacking.packages.importpackages.entities.PackingResult;
import ru.liga.optimalpacking.packages.importpackages.entities.Truck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DensePacking {
    /**
     * Алгоритм максимальной плотности
     * Этот алгоритм стремится упаковать максимальное количество посылок в минимальное число грузовиков.
     * Он полезен, когда важно минимизировать общее количество используемых грузовиков.
     *
     * @param parcels Список посылок для упаковки
     * @return Результат упаковки
     */
    public PackingResult pack(List<Parcel> parcels) {
        // Сортируем посылки по убыванию площади
        List<Parcel> sortedParcels = parcels.stream()
                .sorted(Comparator.comparing(this::calculateArea).reversed())
                .toList();

        List<Truck> trucks = new ArrayList<>();
        List<Parcel> notPackedParcels = new ArrayList<>();
        Truck currentTruck = new Truck();
        trucks.add(currentTruck);

        for (Parcel parcel : sortedParcels) {
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

    /**
     * Рассчитывает площадь посылки, учитывая её форму
     *
     * @param parcel Посылка
     * @return Площадь посылки
     */
    private int calculateArea(Parcel parcel) {
        int area = 0;
        for (char[] row : parcel.form()) {
            for (char cell : row) {
                if (cell == '#') {
                    area++;
                }
            }
        }
        return area;
    }
}