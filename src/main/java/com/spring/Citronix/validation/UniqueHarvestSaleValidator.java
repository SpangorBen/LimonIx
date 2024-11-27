package com.spring.Citronix.validation;

import com.spring.Citronix.annotations.UniqueHarvestSale;
import com.spring.Citronix.dtos.SaleRequestDTO;
import com.spring.Citronix.repositories.SaleRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueHarvestSaleValidator implements ConstraintValidator<UniqueHarvestSale, SaleRequestDTO> {

    private final SaleRepository saleRepository;

    public UniqueHarvestSaleValidator(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public boolean isValid(SaleRequestDTO dto, ConstraintValidatorContext context) {
        if (dto.getHarvestId() == null) {
            return true;
        }

        return !saleRepository.existsByHarvest_Id(dto.getHarvestId());
    }
}

