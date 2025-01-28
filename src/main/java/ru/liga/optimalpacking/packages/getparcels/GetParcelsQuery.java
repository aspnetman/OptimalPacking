package ru.liga.optimalpacking.packages.getparcels;

import an.awesome.pipelinr.Command;
import ru.liga.optimalpacking.packages.getparcels.dto.GetParcelsResponse;

public record GetParcelsQuery(int offset, int limit) implements Command<GetParcelsResponse> {
}
