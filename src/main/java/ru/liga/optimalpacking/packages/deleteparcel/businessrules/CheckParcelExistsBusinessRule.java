package ru.liga.optimalpacking.packages.deleteparcel.businessrules;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.deleteparcel.DeleteParcelsRepository;
import ru.liga.optimalpacking.packages.shared.businessRules.BusinessRule;
import ru.liga.optimalpacking.packages.shared.businessRules.BusinessRuleValidationResult;

@RequiredArgsConstructor
public class CheckParcelExistsBusinessRule implements BusinessRule<String> {
    private final DeleteParcelsRepository deleteParcelsRepository;

    @Override
    public BusinessRuleValidationResult validate(String parcelName) {
        var result = new BusinessRuleValidationResult();
        result.setIsBroken(false);

        if (deleteParcelsRepository.getParcel(parcelName) == null) {
            result.setIsBroken(true);
            result.setErrorMessage("Посылка c именем %s не существует".formatted(parcelName));
        }

        return result;
    }
}
