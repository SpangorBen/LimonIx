package com.spring.Citronix.annotations;

import com.spring.Citronix.validation.SaleDateAfterHarvestValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SaleDateAfterHarvestValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSaleDate {
    String message() default "Sale date must be after the harvest date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

