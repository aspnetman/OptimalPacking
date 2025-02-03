package ru.liga.optimalpacking.packages.exportpackages;

import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import ru.liga.optimalpacking.packages.exportpackages.entities.Truck;

import java.io.FileWriter;
import java.util.List;

public class ExportPackagesParcelsRepository {
    @SneakyThrows
    public void writeParcelsFromTrucksToFile(List<Truck> trucks, String fileName) {
        var parcels = trucks
                .stream()
                .flatMap(truck -> truck.parcels().stream())
                .toList();

        try(FileWriter writer = new FileWriter(fileName)) {
            var gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(parcels, writer);
        }
    }
}