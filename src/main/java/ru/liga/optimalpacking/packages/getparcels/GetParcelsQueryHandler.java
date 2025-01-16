package ru.liga.optimalpacking.packages.getparcels;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.getparcels.dto.GetParcelsResponse;

@RequiredArgsConstructor
public class GetParcelsQueryHandler implements Command.Handler<GetParcelsQuery, GetParcelsResponse> {

    private final GetParcelsRepository getParcelsRepository;

    @Override
    public GetParcelsResponse handle(GetParcelsQuery getParcelsQuery) {
        return new GetParcelsResponse(getParcelsRepository.getParcels());
    }
}
