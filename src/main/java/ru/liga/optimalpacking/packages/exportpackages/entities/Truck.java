package ru.liga.optimalpacking.packages.exportpackages.entities;

import lombok.Getter;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

import java.util.List;
import java.util.UUID;

@Getter
public class Truck {

    private UUID id;

    private List<Parcel> parcels;

    private int occupiedSegmentsCount;

    public int getParcelsCount() {
        return parcels.size();
    }
}
