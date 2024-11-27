package com.spring.Citronix.validation;

import com.spring.Citronix.annotations.ValidSaleDate;
import com.spring.Citronix.dtos.SaleRequestDTO;
import com.spring.Citronix.entities.Harvest;
import com.spring.Citronix.mappers.GenericEntityResolver;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SaleDateAfterHarvestValidator implements ConstraintValidator<ValidSaleDate, SaleRequestDTO> {

    private final GenericEntityResolver resolver;

    public SaleDateAfterHarvestValidator(GenericEntityResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public boolean isValid(SaleRequestDTO dto, ConstraintValidatorContext context) {
        if (dto.getHarvestId() == null || dto.getDate() == null) {
            return true;
        }

        Harvest harvest = resolver.findEntityById(Harvest.class, dto.getHarvestId());
        if (harvest == null) {
            return false;
        }

        return !dto.getDate().isBefore(harvest.getDate());
    }
}

