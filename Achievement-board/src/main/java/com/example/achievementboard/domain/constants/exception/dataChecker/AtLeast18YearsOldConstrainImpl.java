package com.example.achievementboard.domain.constants.exception.dataChecker;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AtLeast18YearsOldConstrainImpl  implements ConstraintValidator<AtLeast18YearsOldConstrain, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if(value == null){return false;}
        LocalDate today = LocalDate.now();

        Period p = Period.between(value, today);
        int years = p.getYears();
        return years >= 18;
    }
}
