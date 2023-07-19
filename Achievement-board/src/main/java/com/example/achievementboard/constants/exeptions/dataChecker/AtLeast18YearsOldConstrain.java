package com.example.achievementboard.constants.exeptions.dataChecker;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy  = AtLeast18YearsOldConstrainImpl.class)
@Target({ElementType.METHOD , ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeast18YearsOldConstrain {
    String message () default "you must be at least 18 year old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
