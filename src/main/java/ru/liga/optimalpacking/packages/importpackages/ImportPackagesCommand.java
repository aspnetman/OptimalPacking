package ru.liga.optimalpacking.packages.importpackages;

import lombok.Data;

@Data
public class ImportPackagesCommand {

    private final String pathToFile;

    public ImportPackagesCommand(String pathToFile) {
        this.pathToFile = pathToFile;
    }
}
