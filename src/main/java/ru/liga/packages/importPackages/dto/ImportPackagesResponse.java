package ru.liga.packages.importPackages.dto;

import lombok.Data;

@Data
public class ImportPackagesResponse {
    private final int totalMachinesNeeded;

    public ImportPackagesResponse(int totalMachinesNeeded) {
        this.totalMachinesNeeded = totalMachinesNeeded;
    }
}
