package ru.liga.optimalpacking.packages.exportpackages;

import an.awesome.pipelinr.Command;
import ru.liga.optimalpacking.packages.exportpackages.dto.ExportPackagesResponse;

public record ExportPackagesCommand(String userId, String trucksFile) implements Command<ExportPackagesResponse> {
}
