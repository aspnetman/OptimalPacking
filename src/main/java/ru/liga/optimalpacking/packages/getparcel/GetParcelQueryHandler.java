package ru.liga.optimalpacking.packages.getparcel;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.packages.getparcel.dto.GetParcelResponse;

@Component
@RequiredArgsConstructor
public class GetParcelQueryHandler implements Command.Handler<GetParcelQuery, GetParcelResponse> {

    private final GetParcelRepository getParcelRepository;

    @Override
    public GetParcelResponse handle(GetParcelQuery getPackageQuery) {

        var parcel = getParcelRepository.getParcel(getPackageQuery.packageName());

        if (parcel == null) {
            return null;
        }

        return new GetParcelResponse(parcel);
    }
}
