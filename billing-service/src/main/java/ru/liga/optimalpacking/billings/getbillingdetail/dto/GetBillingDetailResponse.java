package ru.liga.optimalpacking.billings.getbillingdetail.dto;

import ru.liga.optimalpacking.billings.shared.entities.Billing;

import java.util.List;

public record GetBillingDetailResponse(List<Billing> billings) {
}
