package ru.liga.optimalpacking.packages.importpackages.businessRules;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.importpackages.entities.Truck;
import ru.liga.optimalpacking.packages.shared.exceptions.InvalidOperationException;

import java.util.List;

@RequiredArgsConstructor
public class BusinessRulesChecker {

    private final CheckFilledTrucksExceededMaxValueBusinessRule checkFilledTrucksExceededMaxValueBusinessRule;

    public void checkFilledTrucksExceededMaxValue(List<Truck> filledTrucks, Integer maxTrucks) {
        var result = checkFilledTrucksExceededMaxValueBusinessRule.validate(filledTrucks, maxTrucks);

        if (result.getIsBroken()) {
            throw new InvalidOperationException(result.getErrorMessage());
        }
    }
}
