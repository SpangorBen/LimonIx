package com.spring.Citronix.annotations;

import com.spring.Citronix.validation.FieldSeasonValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = FieldSeasonValidator.class)
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFieldSeason {
    String message() default "A harvest already exists for this field and season.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
