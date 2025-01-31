package ru.liga.optimalpacking.packages.exportpackages.entities;

import ru.liga.optimalpacking.packages.shared.entities.Parcel;

import java.util.List;
import java.util.UUID;

public record Truck(UUID id, List<Parcel> parcels, int occupiedSegmentsCount) {

    public int getParcelsCount() {
        return parcels.size();
    }
}
