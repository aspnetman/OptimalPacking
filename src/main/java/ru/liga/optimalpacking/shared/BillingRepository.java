package ru.liga.optimalpacking.shared;

import ru.liga.optimalpacking.shared.entities.Billing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BillingRepository {
    private final Map<String, List<Billing>> billingMap = new HashMap<>();

    public void addBilling(Billing billing) {
        List<Billing> list = billingMap.getOrDefault(billing.userId(), new ArrayList<>());
        list.add(billing);
        billingMap.put(billing.userId(), list);
    }

    public List<Billing> getBillings(String userId, String fromDate, String toDate) {

        return billingMap.getOrDefault(userId, List.of())
                .stream()
                .filter(billing -> billing.date().isAfter(LocalDate.parse(fromDate)))
                .filter(billing -> billing.date().isBefore(LocalDate.parse(toDate)))
                .sorted(Comparator.comparing(Billing::date).reversed())
                .collect(Collectors.toList());
    }
}
