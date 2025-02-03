package ru.liga.optimalpacking.billings.addbilling;

import org.mapstruct.Mapper;
import ru.liga.optimalpacking.billings.addbilling.dto.BillingDto;
import ru.liga.optimalpacking.billings.shared.entities.Billing;

@Mapper
public interface AddBillingMapper {
    Billing toEntity(BillingDto billingDto);
}
