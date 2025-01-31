package ru.liga.optimalpacking.packages.editparcel;

import an.awesome.pipelinr.Command;
import ru.liga.optimalpacking.packages.editparcel.dto.EditParcelResponse;
import ru.liga.optimalpacking.packages.editparcel.dto.ParcelDto;

public record EditParcelCommand(String name, ParcelDto parcelDto) implements Command<EditParcelResponse> {
}
