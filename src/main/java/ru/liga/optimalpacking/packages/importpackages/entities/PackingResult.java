package ru.liga.optimalpacking.packages.importpackages.entities;

import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;

import java.util.List;

public record PackingResult(List<Truck> trucks, List<Parcel> notPackedParcels) {
}
