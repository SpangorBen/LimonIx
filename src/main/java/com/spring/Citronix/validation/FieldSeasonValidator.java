package com.spring.Citronix.validation;


import com.spring.Citronix.annotations.ValidFieldSeason;
import com.spring.Citronix.dtos.HarvestRequestDTO;
import com.spring.Citronix.entities.enums.Season;
import com.spring.Citronix.repositories.HarvestRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class FieldSeasonValidator implements ConstraintValidator<ValidFieldSeason, HarvestRequestDTO> {

    @Autowired
    private HarvestRepository harvestRepository;

    @Override
    public boolean isValid(HarvestRequestDTO dto, ConstraintValidatorContext context) {
        if (dto.getFieldId() == null || dto.getSeason() == null) {
            return true;
        }

        Season season;
        try {
            season = Season.valueOf(dto.getSeason().toUpperCase());
        } catch (IllegalArgumentException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid season value provided.")
                    .addPropertyNode("season")
                    .addConstraintViolation();
            return false;
        }

        // Check if a harvest already exists for the given fieldId and season
        boolean exists = harvestRepository.existsByFieldIdAndSeason(dto.getFieldId(), season);

        if (exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("A harvest already exists for this field and season.")
                    .addPropertyNode("fieldId") // You can specify a specific field if needed
                    .addConstraintViolation();
        }

        return !exists;
    }
}