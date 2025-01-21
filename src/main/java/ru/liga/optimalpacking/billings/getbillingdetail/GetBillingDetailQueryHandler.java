package ru.liga.optimalpacking.billings.getbillingdetail;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.billings.getbillingdetail.dto.GetBillingDetailResponse;
import ru.liga.optimalpacking.shared.entities.Billing;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetBillingDetailQueryHandler implements Command.Handler<GetBillingDetailQuery, GetBillingDetailResponse> {

    private final GetBillingDetailRepository getBillingDetailRepository;

    @Override
    public GetBillingDetailResponse handle(GetBillingDetailQuery getBillingDetailQuery) {

        var billings = getBillingDetailRepository.GetBillings(
                getBillingDetailQuery.userId(),
                getBillingDetailQuery.from(),
                getBillingDetailQuery.to());

        if (billings.isEmpty()) {
            return null;
        }

        return new GetBillingDetailResponse(billings
                .stream()
                .map(Billing::description)
                .collect(Collectors.joining("\n")));
    }
}
