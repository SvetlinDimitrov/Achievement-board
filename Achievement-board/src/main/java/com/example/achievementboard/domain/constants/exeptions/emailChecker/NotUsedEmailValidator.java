package com.example.achievementboard.domain.constants.exeptions.emailChecker;

import com.example.achievementboard.service.user.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotUsedEmailValidator implements ConstraintValidator<NotUsedEmailConstraint, String> {
    private UserService service;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return service.notUsedEmail(value);
    }
}
