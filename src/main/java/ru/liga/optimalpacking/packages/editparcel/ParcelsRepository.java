package ru.liga.optimalpacking.packages.editparcel;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

@RequiredArgsConstructor
public class ParcelsRepository {
    private final ru.liga.optimalpacking.packages.shared.ParcelsRepository parcelsRepository;

    public void editParcel(String name, Parcel parcel) {
        parcelsRepository.editParcel(name, parcel);
    }

    public Parcel getParcel(String name) {
        return parcelsRepository.getParcel(name);
    }
}
