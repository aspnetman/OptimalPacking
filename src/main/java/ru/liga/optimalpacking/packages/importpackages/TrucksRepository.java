package ru.liga.optimalpacking.packages.importpackages;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import ru.liga.optimalpacking.packages.importpackages.entities.Truck;

public class TrucksRepository {

    @SneakyThrows(IOException.class)
    public void saveResultsToJSON(List<Truck> trucks, String filename) {

        if (trucks.isEmpty()) {
            return;
        }

        try(FileWriter writer = new FileWriter(filename)) {
            var gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(trucks, writer);
        }
    }
}