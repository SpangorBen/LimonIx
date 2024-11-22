package com.spring.Citronix.validation;

import com.spring.Citronix.annotations.ValidFieldArea;
import com.spring.Citronix.dtos.FieldRequestDTO;
import com.spring.Citronix.entities.Farm;
import com.spring.Citronix.repositories.FarmRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidFieldAreaValidator implements ConstraintValidator<ValidFieldArea, FieldRequestDTO> {

    private final FarmRepository farmRepository;

    @Override
    public boolean isValid(FieldRequestDTO dto, ConstraintValidatorContext context) {
        if (dto.getFarmId() == null || dto.getArea() == null) {
            return true;
        }

        Farm farm = farmRepository.findById(dto.getFarmId()).orElse(null);
        if (farm == null) {
            return false;
        }

        return dto.getArea() <= farm.getArea() * 0.5;
    }
}
