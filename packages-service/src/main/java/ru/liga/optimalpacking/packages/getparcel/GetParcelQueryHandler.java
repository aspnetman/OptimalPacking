package ru.liga.optimalpacking.packages.getparcel;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.packages.getparcel.dto.GetParcelResponse;
import ru.liga.optimalpacking.packages.shared.ParcelsRepository;

@Component
@RequiredArgsConstructor
public class GetParcelQueryHandler implements Command.Handler<GetParcelQuery, GetParcelResponse> {

    private final ParcelsRepository parcelsRepository;

    @Override
    public GetParcelResponse handle(GetParcelQuery getPackageQuery) {

        var parcel = parcelsRepository.findById(getPackageQuery.packageName())
                .orElse(null);

        if (parcel == null) {
            return null;
        }

        return new GetParcelResponse(parcel);
    }
}
