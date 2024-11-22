package com.spring.Citronix.annotations;

import com.spring.Citronix.validation.PlantationDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = PlantationDateValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPlantationDate {
    String message() default "Plantation date must be between March and May";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
