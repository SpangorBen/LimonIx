package com.spring.Citronix.validation;

import com.spring.Citronix.annotations.ValidPlantationDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class PlantationDateValidator implements ConstraintValidator<ValidPlantationDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context){
        if (date == null) {
            return true;
        }

        int month = date.getMonthValue();
        return month >= 3 && month <= 5;
    }
}
