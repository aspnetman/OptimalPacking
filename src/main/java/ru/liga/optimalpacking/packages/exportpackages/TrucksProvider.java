package ru.liga.optimalpacking.packages.exportpackages;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import ru.liga.optimalpacking.packages.exportpackages.entities.Truck;

import java.util.Arrays;
import java.util.List;

public class TrucksProvider {
    @SneakyThrows
    public List<Truck> getTrucksFromJson(String json) {

        var trucks = new Gson().fromJson(json, Truck[].class);

        return Arrays.stream(trucks).toList();
    }
}
