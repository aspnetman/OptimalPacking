package ru.liga.optimalpacking.packages.importpackages;

import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import ru.liga.optimalpacking.packages.importpackages.entities.Truck;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TrucksRepository {

    @SneakyThrows(IOException.class)
    public void saveResultsToJson(List<Truck> trucks, String filename) {

        if (trucks.isEmpty()) {
            return;
        }

        try (FileWriter writer = new FileWriter(filename)) {
            var gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(trucks, writer);
        }
    }
}