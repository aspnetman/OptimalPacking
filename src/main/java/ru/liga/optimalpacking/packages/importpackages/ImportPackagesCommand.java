package ru.liga.optimalpacking.packages.importpackages;

import an.awesome.pipelinr.Command;
import ru.liga.optimalpacking.packages.importpackages.dto.ImportPackagesResponse;
import ru.liga.optimalpacking.packages.importpackages.dto.PackingAlgorithm;

public record ImportPackagesCommand(String file, Integer maxTrucks, PackingAlgorithm packingAlgorithm) implements Command<ImportPackagesResponse> {
}
