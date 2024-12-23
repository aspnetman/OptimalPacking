package ru.liga.optimalpacking.packages.exportpackages;

import an.awesome.pipelinr.Command;
import org.json.JSONObject;
import ru.liga.optimalpacking.packages.exportpackages.dto.ExportPackagesResponse;

public record ExportPackagesCommand(JSONObject machines) implements Command<ExportPackagesResponse> {
}
