package ru.liga.optimalpacking.packages.shared.businessRules;

public interface BusinessRule<TArg0> {
    BusinessRuleValidationResult validate(TArg0 arg0);
}