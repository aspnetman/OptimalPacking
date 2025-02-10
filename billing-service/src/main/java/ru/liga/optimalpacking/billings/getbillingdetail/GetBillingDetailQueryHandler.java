package ru.liga.optimalpacking.billings.getbillingdetail;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.billings.getbillingdetail.dto.GetBillingDetailResponse;
import ru.liga.optimalpacking.billings.shared.BillingRepository;
import ru.liga.optimalpacking.billings.shared.entities.Billing;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetBillingDetailQueryHandler implements Command.Handler<GetBillingDetailQuery, GetBillingDetailResponse> {

    private final BillingRepository billingRepository;

    @Cacheable(value = "billings", key = "#getBillingDetailQuery.userId() + '-range'")
    @Override
    public GetBillingDetailResponse handle(GetBillingDetailQuery getBillingDetailQuery) {

        var billings = billingRepository.findByUserIdAndDateBetweenOrderByDateDesc(
                getBillingDetailQuery.userId(),
                getBillingDetailQuery.from(),
                getBillingDetailQuery.to());

        if (billings.isEmpty()) {
            return null;
        }

        return new GetBillingDetailResponse(billings
                .stream()
                .map(Billing::getDescription)
                .collect(Collectors.joining("\n")));
    }
}