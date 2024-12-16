package ru.liga.packages.importPackages;

import ru.liga.packages.importPackages.entities.Parcel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileParcer {
    public static List<Parcel> readParcelsFromFile(String fileName) throws Exception {
        List<Parcel> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int width = Integer.parseInt(parts[0].trim());
                int height = Integer.parseInt(parts[1].trim());

                result.add(new Parcel(width, height));
            }
        }

        return result;
    }
}
