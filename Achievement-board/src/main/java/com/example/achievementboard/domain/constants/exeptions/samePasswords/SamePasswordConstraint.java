package com.example.achievementboard.domain.constants.exeptions.samePasswords;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy  = SamePasswordConstraintValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SamePasswordConstraint {
    String message () default "passwords dose not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
