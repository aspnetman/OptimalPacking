package ru.liga.optimalpacking.packages.editparcel.businessrules;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.shared.ParcelsRepository;
import ru.liga.optimalpacking.packages.shared.businessRules.BusinessRule;
import ru.liga.optimalpacking.packages.shared.businessRules.BusinessRuleValidationResult;

@RequiredArgsConstructor
public class CheckParcelExistsBusinessRule implements BusinessRule<String> {
    private final ParcelsRepository parcelsRepository;

    @Override
    public BusinessRuleValidationResult validate(String parcelName) {
        var result = new BusinessRuleValidationResult();
        result.setIsBroken(false);

        if (parcelsRepository.findById(parcelName).orElse(null) == null) {
            result.setIsBroken(true);
            result.setErrorMessage("Посылка c именем %s не существует".formatted(parcelName));
        }

        return result;
    }
}