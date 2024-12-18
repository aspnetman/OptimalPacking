package ru.liga.optimalpacking.packages.importpackages;

import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;

import java.util.List;

public record ImportPackagesCommand(List<Parcel> parcels) {
}
