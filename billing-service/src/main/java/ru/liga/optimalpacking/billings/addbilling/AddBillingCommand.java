package ru.liga.optimalpacking.billings.addbilling;

import an.awesome.pipelinr.Command;
import ru.liga.optimalpacking.billings.addbilling.dto.AddBillingResponse;
import ru.liga.optimalpacking.billings.addbilling.dto.BillingDto;

public record AddBillingCommand(BillingDto billingDto) implements Command<AddBillingResponse> {
}