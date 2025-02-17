package ru.liga.optimalpacking.packages.importpackages.dto;

import ru.liga.optimalpacking.packages.importpackages.entities.Truck;

import java.util.List;

public record ImportPackagesResponse(List<Truck> filledTrucks) {
}
