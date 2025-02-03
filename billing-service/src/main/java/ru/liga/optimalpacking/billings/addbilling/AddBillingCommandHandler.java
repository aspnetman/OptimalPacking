package ru.liga.optimalpacking.billings.addbilling;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.billings.addbilling.dto.AddBillingResponse;
import ru.liga.optimalpacking.billings.shared.BillingRepository;

@Component
@RequiredArgsConstructor
public class AddBillingCommandHandler implements Command.Handler<AddBillingCommand, AddBillingResponse> {

    private final BillingRepository billingRepository;

    private final AddBillingMapper addBillingMapper;

    @Override
    public AddBillingResponse handle(AddBillingCommand addBillingCommand) {

        billingRepository.save(addBillingMapper.toEntity(addBillingCommand.billingDto()));

        return new AddBillingResponse();
    }
}
