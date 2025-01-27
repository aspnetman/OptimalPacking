package ru.liga.optimalpacking.packages.exportpackages;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import ru.liga.optimalpacking.packages.exportpackages.entities.Truck;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class TrucksProvider {
    @SneakyThrows(IOException.class)
    public List<Truck> getTrucksFromFile(String fileName) {

        try (InputStream inputStream = new FileInputStream(fileName)) {
            var reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            var trucks = new Gson().fromJson(reader, Truck[].class);

            return Arrays.stream(trucks).toList();
        }
    }
}
