package ru.liga.optimalpacking.packages.exportpackages;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.shared.BillingRepository;
import ru.liga.optimalpacking.shared.entities.Billing;

@RequiredArgsConstructor
public class ExportPackagesBillingRepository {

    private final BillingRepository billingRepository;

    public void addBilling(Billing billing) {
        billingRepository.addBilling(billing);
    }
}
