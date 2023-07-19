package com.example.achievementboard.constants.exeptions.samePasswords;

import com.example.achievementboard.constants.dtos.LoginUser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SamePasswordConstraintValidator implements ConstraintValidator<SamePasswordConstraint , LoginUser> {
    @Override
    public boolean isValid(LoginUser value, ConstraintValidatorContext context) {
        return value.getPassword() != null && value.getConfirmPassword() != null && value.getPassword().equals(value.getConfirmPassword());
    }
}
