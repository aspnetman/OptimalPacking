package ru.liga.optimalpacking.billings.addbilling;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import ru.liga.optimalpacking.billings.addbilling.dto.BillingDto;
import ru.liga.optimalpacking.billings.shared.entities.Billing;

import java.math.BigDecimal;

@Mapper
public interface AddBillingMapper {
    Billing toEntity(BillingDto billingDto, @Context BigDecimal cost);
}
