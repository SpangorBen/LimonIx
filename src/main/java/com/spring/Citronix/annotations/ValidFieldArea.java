package com.spring.Citronix.annotations;

import com.spring.Citronix.validation.ValidFieldAreaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidFieldAreaValidator.class)
@Documented
public @interface ValidFieldArea {
    String message() default "Field area exceeds 50% of the farm's total area";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}