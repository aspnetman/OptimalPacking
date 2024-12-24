package ru.liga.optimalpacking.packages.exportpackages.entities;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Truck {

    private UUID id;

    private List<Parcel> parcels;
}
