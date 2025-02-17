package ru.liga.optimalpacking.packages.importpackages.businessrules;

import ru.liga.optimalpacking.packages.shared.businessRules.BusinessRule;
import ru.liga.optimalpacking.packages.shared.businessRules.BusinessRuleValidationResult;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

import java.util.List;

public class CheckFilledTrucksExceededMaxValueBusinessRule implements BusinessRule<List<Parcel>> {
    @Override
    public BusinessRuleValidationResult validate(List<Parcel> notPackedParcels) {
        var result = new BusinessRuleValidationResult();
        result.setIsBroken(false);

        if (!notPackedParcels.isEmpty()) {
            result.setIsBroken(true);
            result.setErrorMessage("В грузовики не поместилось %d посылок".formatted(notPackedParcels.size()));
        }

        return result;
    }
}
