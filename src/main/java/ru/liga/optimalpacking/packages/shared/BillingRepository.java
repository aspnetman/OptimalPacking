package ru.liga.optimalpacking.packages.shared;

import ru.liga.optimalpacking.packages.importpackages.entities.Billing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillingRepository {
    private final Map<String, List<Billing>> billingMap = new HashMap<>();

    public void addBilling(Billing billing) {
        List<Billing> list = billingMap.getOrDefault(billing.getUserId(), new ArrayList<>());
        list.add(billing);
        billingMap.put(billing.getUserId(), list);
    }
}
