package ru.liga.optimalpacking.packages.getparcel;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.getparcel.dto.GetParcelResponse;

@RequiredArgsConstructor
public class GetParcelQueryHandler implements Command.Handler<GetParcelQuery, GetParcelResponse> {

    private final ParcelsRepository parcelsRepository;

    @Override
    public GetParcelResponse handle(GetParcelQuery getPackageQuery) {

        var parcel = parcelsRepository.getParcel(getPackageQuery.packageName());

        if (parcel == null) {
            return null;
        }

        return new GetParcelResponse(parcel);
    }
}
