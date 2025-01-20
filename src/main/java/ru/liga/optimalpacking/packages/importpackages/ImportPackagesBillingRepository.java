package ru.liga.optimalpacking.packages.importpackages;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.importpackages.entities.Billing;
import ru.liga.optimalpacking.packages.shared.BillingRepository;

@RequiredArgsConstructor
public class ImportPackagesBillingRepository {

    private final BillingRepository billingRepository;

    public void addBilling(Billing billing) {
        billingRepository.addBilling(billing);
    }
}