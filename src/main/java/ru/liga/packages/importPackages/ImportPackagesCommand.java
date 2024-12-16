package ru.liga.packages.importPackages;

import lombok.Data;

@Data
public class ImportPackagesCommand {

    private final String pathToFile;

    public ImportPackagesCommand(String pathToFile) {
        this.pathToFile = pathToFile;
    }
}
