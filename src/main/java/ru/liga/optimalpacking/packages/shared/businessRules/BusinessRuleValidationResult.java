package ru.liga.optimalpacking.packages.shared.businessRules;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessRuleValidationResult {

    private Boolean isBroken;

    private String errorMessage;
}
