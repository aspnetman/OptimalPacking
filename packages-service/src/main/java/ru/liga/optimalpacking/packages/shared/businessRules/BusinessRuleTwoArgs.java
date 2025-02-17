package ru.liga.optimalpacking.packages.shared.businessRules;

public interface BusinessRuleTwoArgs<TArg0, TArg1> {
    BusinessRuleValidationResult validate(TArg0 arg0, TArg1 arg1);
}
