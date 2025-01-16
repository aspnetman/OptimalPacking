package ru.liga.optimalpacking.packages.getparcels;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

import java.util.List;

@RequiredArgsConstructor
public class ParcelsRepository {

    private final ru.liga.optimalpacking.packages.shared.ParcelsRepository parcelsRepository;

    public List<Parcel> getParcels() {
        return parcelsRepository.getParcels();
    }
}