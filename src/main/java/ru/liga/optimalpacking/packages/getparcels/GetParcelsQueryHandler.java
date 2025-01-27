package ru.liga.optimalpacking.packages.getparcels;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.packages.getparcels.dto.GetParcelsResponse;

@Component
@RequiredArgsConstructor
public class GetParcelsQueryHandler implements Command.Handler<GetParcelsQuery, GetParcelsResponse> {

    private final GetParcelsRepository getParcelsRepository;

    @Override
    public GetParcelsResponse handle(GetParcelsQuery getParcelsQuery) {
        return new GetParcelsResponse(getParcelsRepository.getParcels());
    }
}
