package ru.liga.optimalpacking.packages.importpackages;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;
import ru.liga.optimalpacking.packages.importpackages.entities.Truck;

public class TrucksRepository {

    @SneakyThrows(IOException.class)
    public void saveResultsToJSON(List<Truck> trucks, String filename) {

        if (trucks.isEmpty()) {
            return;
        }

        JSONObject jsonObject = new JSONObject();

        JSONArray machinesArray = new JSONArray();
        jsonObject.put("machines", machinesArray);

        for (Truck truck : trucks) {
            JSONObject machineObject = getJsonObject(truck);
            machinesArray.put(machineObject);
        }

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(jsonObject.toString());
            writer.flush();
        }
    }

    private static JSONObject getJsonObject(Truck truck) {
        JSONObject machineObject = new JSONObject();

        machineObject.put("machine_id", truck.getId());

        JSONArray parcelsArray = new JSONArray();

        for (Parcel parcel : truck.getParcels()) {
            JSONArray dimensionsArray = new JSONArray();
            dimensionsArray.put(parcel.getWidth());
            dimensionsArray.put(parcel.getHeight());
            parcelsArray.put(dimensionsArray);
        }

        machineObject.put("parcels", parcelsArray);
        return machineObject;
    }
}
