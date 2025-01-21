package ru.liga.optimalpacking.billings.getbillingdetail;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.shared.BillingRepository;
import ru.liga.optimalpacking.shared.entities.Billing;

import java.util.List;

@RequiredArgsConstructor
public class GetBillingDetailRepository {

    private final BillingRepository billingRepository;

    public List<Billing> GetBillings(String userId, String fromDate, String toDate) {
        return billingRepository.getBillings(userId, fromDate, toDate);
    }
}
