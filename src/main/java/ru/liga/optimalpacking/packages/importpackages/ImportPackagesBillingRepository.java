package ru.liga.optimalpacking.packages.importpackages;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.shared.BillingRepository;
import ru.liga.optimalpacking.shared.entities.Billing;

@RequiredArgsConstructor
public class ImportPackagesBillingRepository {

    private final BillingRepository billingRepository;

    public void addBilling(Billing billing) {
        billingRepository.addBilling(billing);
    }
}