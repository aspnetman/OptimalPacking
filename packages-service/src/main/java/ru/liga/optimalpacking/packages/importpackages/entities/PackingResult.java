package ru.liga.optimalpacking.packages.importpackages.entities;

import ru.liga.optimalpacking.packages.shared.entities.Parcel;

import java.util.List;

public record PackingResult(List<Truck> trucks, List<Parcel> notPackedParcels) {
}
