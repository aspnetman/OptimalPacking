package ru.liga.optimalpacking.packages.importpackages;

import an.awesome.pipelinr.Command;
import ru.liga.optimalpacking.packages.importpackages.dto.ImportPackagesResponse;
import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;

import java.util.List;
import java.util.Optional;

public record ImportPackagesCommand(List<Parcel> parcels, Integer maxTrucks) implements Command<ImportPackagesResponse> {
}
