package ru.liga.optimalpacking.packages.getparcels;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.packages.getparcels.dto.GetParcelsResponse;
import ru.liga.optimalpacking.packages.shared.ParcelsRepository;

@Component
@RequiredArgsConstructor
public class GetParcelsQueryHandler implements Command.Handler<GetParcelsQuery, GetParcelsResponse> {

    private final ParcelsRepository parcelsRepository;

    @Override
    public GetParcelsResponse handle(GetParcelsQuery getParcelsQuery) {

        var page = parcelsRepository
                .findAll(
                        PageRequest.of(getParcelsQuery.offset() / getParcelsQuery.limit(),
                                getParcelsQuery.limit(),
                                Sort.by("name").ascending()));

        return new GetParcelsResponse(page.getContent());
    }
}
