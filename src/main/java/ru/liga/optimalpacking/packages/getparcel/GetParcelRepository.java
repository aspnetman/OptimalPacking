package ru.liga.optimalpacking.packages.getparcel;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

@RequiredArgsConstructor
public class GetParcelRepository {

    private final ru.liga.optimalpacking.packages.shared.ParcelsRepository parcelsRepository;

    public Parcel getParcel(String name) {
        return parcelsRepository.getParcel(name);
    }
}
