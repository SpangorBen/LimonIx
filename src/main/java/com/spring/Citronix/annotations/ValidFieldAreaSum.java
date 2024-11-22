package com.spring.Citronix.annotations;


import com.spring.Citronix.validation.FieldAreaSumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FieldAreaSumValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFieldAreaSum {
    String message() default "The total area of fields exceeds the farm's total area";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

