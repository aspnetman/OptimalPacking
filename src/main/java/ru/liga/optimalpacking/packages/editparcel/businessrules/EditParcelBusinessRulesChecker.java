package ru.liga.optimalpacking.packages.editparcel.businessrules;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.shared.exceptions.NotFoundException;

@RequiredArgsConstructor
public class EditParcelBusinessRulesChecker {
    private final CheckParcelExistsBusinessRule checkParcelExistsBusinessRule;

    public void checkParcelExists(String parcelName) {
        var result = checkParcelExistsBusinessRule.validate(parcelName);

        if (result.getIsBroken()) {
            throw new NotFoundException(result.getErrorMessage());
        }
    }
}