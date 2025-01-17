package ru.liga.optimalpacking.packages.importpackages;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Type;
import java.util.List;

public class FileParcelsReader {
    public List<Parcel> readParcelsFromFile(String fileName) {
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Parcel>>() {
            }.getType();

            try (FileReader reader = new FileReader(fileName)) {
                return gson.fromJson(reader, listType);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}