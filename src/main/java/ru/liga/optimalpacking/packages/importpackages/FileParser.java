package ru.liga.optimalpacking.packages.importpackages;

import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    public List<Parcel> readParcelsFromFile(String fileName) {
        List<Parcel> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int width = Integer.parseInt(parts[0].trim());
                int height = Integer.parseInt(parts[1].trim());

                result.add(new Parcel(width, height));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
