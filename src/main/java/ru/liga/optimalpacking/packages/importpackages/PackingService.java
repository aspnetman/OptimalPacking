package ru.liga.optimalpacking.packages.importpackages;

import lombok.AllArgsConstructor;
import ru.liga.optimalpacking.packages.importpackages.dto.PackingAlgorithm;
import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;
import ru.liga.optimalpacking.packages.importpackages.entities.PackingResult;
import ru.liga.optimalpacking.packages.importpackages.packingAlgorithms.DensePacking;
import ru.liga.optimalpacking.packages.importpackages.packingAlgorithms.UniformPacking;

import java.util.List;

@AllArgsConstructor
public class PackingService {
    private final DensePacking densePacking;
    private final UniformPacking uniformPacking;

    public PackingResult pack(List<Parcel> parcels, Integer maxTrucks, PackingAlgorithm algorithm) {
        return switch (algorithm) {
            case DensePacking -> densePacking.pack(parcels);
            case UniformPacking -> uniformPacking.pack(parcels, maxTrucks);
            default -> throw new IllegalArgumentException("Unknown algorithm");
        };
    }
}