package com.spring.Citronix.annotations;


import com.spring.Citronix.validation.TreeDensityValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TreeDensityValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTreeDensity {
    String message() default "Tree density exceeds the maximum limit of 100 trees per hectare";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

