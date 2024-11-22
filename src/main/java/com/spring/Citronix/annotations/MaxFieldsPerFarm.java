package com.spring.Citronix.annotations;

import com.spring.Citronix.validation.MaxFieldsPerFarmValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxFieldsPerFarmValidator.class)
@Documented
public @interface MaxFieldsPerFarm {
    String message() default "A farm cannot have more than 10 fields";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
