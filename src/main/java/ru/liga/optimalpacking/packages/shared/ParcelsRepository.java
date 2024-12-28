package ru.liga.optimalpacking.packages.shared;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ParcelsRepository {
    private final Map<String, Parcel> parcelsMap;

    public Parcel getParcel(String name) {
        return parcelsMap.get(name);
    }

    public List<Parcel> getParcels() {
        return parcelsMap.values().stream().toList();
    }

    public void deleteParcel(String name) {
        parcelsMap.remove(name);
    }

    public void editParcel(String name, Parcel parcel) {
        parcelsMap.put(name, parcel);
    }
}
