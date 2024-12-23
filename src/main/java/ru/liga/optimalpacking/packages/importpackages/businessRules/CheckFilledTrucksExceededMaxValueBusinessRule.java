package ru.liga.optimalpacking.packages.importpackages.businessRules;

import ru.liga.optimalpacking.packages.importpackages.entities.Truck;
import ru.liga.optimalpacking.packages.shared.businessRules.BusinessRuleTwoArgs;
import ru.liga.optimalpacking.packages.shared.businessRules.BusinessRuleValidationResult;

import java.util.List;

public class CheckFilledTrucksExceededMaxValueBusinessRule implements BusinessRuleTwoArgs<List<Truck>, Integer> {
    @Override
    public BusinessRuleValidationResult validate(List<Truck> trucks, Integer maxTrucks) {
        var result = new BusinessRuleValidationResult();
        result.setIsBroken(false);

        if (maxTrucks != null && trucks.size() > maxTrucks) {
            result.setIsBroken(true);
            result.setErrorMessage("Количество заполненных грузовиков превышает максимальное значение");
        }

        return result;
    }
}
