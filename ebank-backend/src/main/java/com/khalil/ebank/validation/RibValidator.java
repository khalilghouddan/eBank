package com.khalil.ebank.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RibValidator implements ConstraintValidator<ValidRib, String> {

    // Simple RIB validation pattern (e.g., length check 24 digits)
    // Adjust regex as per specific bank rules if needed.
    private static final String RIB_PATTERN = "[a-zA-Z0-9]{24}";

    @Override
    public void initialize(ValidRib constraintAnnotation) {
    }

    @Override
    public boolean isValid(String rib, ConstraintValidatorContext context) {
        if (rib == null) {
            return true; // Let @NotNull handle nulls
        }
        return rib.matches(RIB_PATTERN);
    }
}
