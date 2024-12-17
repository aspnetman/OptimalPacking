package ru.liga.optimalpacking.packages.importpackages.dto;

import lombok.Data;

@Data
public class ImportPackagesResponse {
    private final int totalMachinesNeeded;

    public ImportPackagesResponse(int totalMachinesNeeded) {
        this.totalMachinesNeeded = totalMachinesNeeded;
    }
}
