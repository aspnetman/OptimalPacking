package ru.liga.optimalpacking.packages.getparcels;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.getparcels.dto.GetParcelsResponse;

@RequiredArgsConstructor
public class GetParcelsQueryHandler implements Command.Handler<GetParcelsQuery, GetParcelsResponse> {

    private final ParcelsRepository parcelsRepository;

    @Override
    public GetParcelsResponse handle(GetParcelsQuery getParcelsQuery) {
        return new GetParcelsResponse(parcelsRepository.getParcels());
    }
}
