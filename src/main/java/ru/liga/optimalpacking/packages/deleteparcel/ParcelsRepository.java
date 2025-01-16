package ru.liga.optimalpacking.packages.deleteparcel;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

@RequiredArgsConstructor
public class ParcelsRepository {

    private final ru.liga.optimalpacking.packages.shared.ParcelsRepository parcelsRepository;

    public void deleteParcel(String name) {
        parcelsRepository.deleteParcel(name);
    }

    public Parcel getParcel(String name) {
        return parcelsRepository.getParcel(name);
    }
}
