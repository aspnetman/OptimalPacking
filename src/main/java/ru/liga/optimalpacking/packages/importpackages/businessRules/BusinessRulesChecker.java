package ru.liga.optimalpacking.packages.importpackages.businessRules;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;
import ru.liga.optimalpacking.packages.shared.exceptions.InvalidOperationException;

import java.util.List;

@RequiredArgsConstructor
public class BusinessRulesChecker {

    private final CheckFilledTrucksExceededMaxValueBusinessRule checkFilledTrucksExceededMaxValueBusinessRule;

    public void checkFilledTrucksExceededMaxValue(List<Parcel> notPackedParcels) {
        var result = checkFilledTrucksExceededMaxValueBusinessRule.validate(notPackedParcels);

        if (result.getIsBroken()) {
            throw new InvalidOperationException(result.getErrorMessage());
        }
    }
}
