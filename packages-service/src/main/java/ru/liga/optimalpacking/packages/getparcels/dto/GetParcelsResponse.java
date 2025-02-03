package ru.liga.optimalpacking.packages.getparcels.dto;

import ru.liga.optimalpacking.packages.shared.entities.Parcel;

import java.util.List;

public record GetParcelsResponse(List<Parcel> parcels) {
}
