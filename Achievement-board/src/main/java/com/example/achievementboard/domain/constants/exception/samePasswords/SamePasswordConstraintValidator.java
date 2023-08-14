package com.example.achievementboard.domain.constants.exception.samePasswords;

import com.example.achievementboard.domain.dtos.user.LoginUser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SamePasswordConstraintValidator implements ConstraintValidator<SamePasswordConstraint , LoginUser> {
    @Override
    public boolean isValid(LoginUser value, ConstraintValidatorContext context) {
        return value.getPassword() != null && value.getConfirmPassword() != null && value.getPassword().equals(value.getConfirmPassword());
    }
}
