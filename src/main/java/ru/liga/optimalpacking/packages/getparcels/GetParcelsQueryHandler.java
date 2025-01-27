package ru.liga.optimalpacking.packages.getparcels;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.packages.getparcels.dto.GetParcelsResponse;
import ru.liga.optimalpacking.packages.shared.ParcelsRepository;

@Component
@RequiredArgsConstructor
public class GetParcelsQueryHandler implements Command.Handler<GetParcelsQuery, GetParcelsResponse> {

    private final ParcelsRepository parcelsRepository;

    @Override
    public GetParcelsResponse handle(GetParcelsQuery getParcelsQuery) {
        return new GetParcelsResponse(parcelsRepository.findAll());
    }
}
