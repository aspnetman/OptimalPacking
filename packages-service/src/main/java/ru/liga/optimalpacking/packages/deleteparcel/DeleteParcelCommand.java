package ru.liga.optimalpacking.packages.deleteparcel;

import an.awesome.pipelinr.Command;
import ru.liga.optimalpacking.packages.deleteparcel.dto.DeleteParcelResponse;

public record DeleteParcelCommand(String name) implements Command<DeleteParcelResponse> {
}
