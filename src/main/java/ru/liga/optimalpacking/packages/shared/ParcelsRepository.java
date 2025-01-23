package ru.liga.optimalpacking.packages.shared;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.importpackages.FileParcelsReader;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ParcelsRepository {

    private final FileParcelsReader fileParcelsReader;

    private Map<String, Parcel> parcelsMap;

    @PostConstruct
    public void loadParcels() {
        String FILE = "src/main/resources/packages.txt";

        parcelsMap = fileParcelsReader.readParcelsFromFile(FILE)
                .stream()
                .collect(HashMap::new,
                        (map, parcel) -> map.put(parcel.name(), parcel),
                        HashMap::putAll);
    }

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
        parcelsMap.remove(name);
        parcelsMap.put(parcel.name(), parcel);
    }
}
