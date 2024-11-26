package com.spring.Citronix.annotations;

import com.spring.Citronix.validation.DateSeasonValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = DateSeasonValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidDateSeason {
    String message() default "The date does not match the specified season";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
