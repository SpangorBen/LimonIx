package com.spring.Citronix.annotations;

import com.spring.Citronix.validation.UniqueHarvestSaleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueHarvestSaleValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueHarvestSale {
    String message() default "A sale for this harvest already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

