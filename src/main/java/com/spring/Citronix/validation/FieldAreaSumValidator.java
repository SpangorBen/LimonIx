package com.spring.Citronix.validation;

import com.spring.Citronix.annotations.ValidFieldAreaSum;
import com.spring.Citronix.dtos.FieldRequestDTO;
import com.spring.Citronix.entities.Farm;
import com.spring.Citronix.entities.Field;
import com.spring.Citronix.repositories.FarmRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

public class FieldAreaSumValidator implements ConstraintValidator<ValidFieldAreaSum, FieldRequestDTO> {

    private final FarmRepository farmRepository;

    public FieldAreaSumValidator(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public boolean isValid(FieldRequestDTO fieldRequestDTO, ConstraintValidatorContext context) {
        if (fieldRequestDTO.getFarmId() == null || fieldRequestDTO.getArea() == null) {
            return true; // Skip validation if farm ID or area is not provided
        }

        Farm farm = farmRepository.findById(fieldRequestDTO.getFarmId()).orElse(null);
        if (farm == null) {
            return true; // Skip validation if farm does not exist; handled by @Exists
        }

        // Calculate the total area of fields
        double currentTotalArea = farm.getFields().stream()
                .mapToDouble(Field::getArea)
                .sum();

        // Add the new field's area
        double newTotalArea = currentTotalArea + fieldRequestDTO.getArea();

        return newTotalArea < farm.getArea(); // Ensure the total area is less than the farm's area
    }
}