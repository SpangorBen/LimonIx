package com.spring.Citronix.validation;

import com.spring.Citronix.annotations.ValidTreeDensity;
import com.spring.Citronix.dtos.TreeRequestDTO;
import com.spring.Citronix.entities.Field;
import com.spring.Citronix.repositories.FieldRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class TreeDensityValidator implements ConstraintValidator<ValidTreeDensity, TreeRequestDTO> {

    @Autowired
    private FieldRepository fieldRepository;

    @Override
    public boolean isValid(TreeRequestDTO treeRequestDTO, ConstraintValidatorContext context) {
        if (treeRequestDTO.getFieldId() == null) {
            return true;
        }

        Field field = fieldRepository.findById(treeRequestDTO.getFieldId()).orElse(null);
        if (field == null) {
            return true;
        }

        double fieldAreaInHectares = field.getArea();
        long maxAllowedTrees = (long) (fieldAreaInHectares * 100);

        long currentTreeCount = field.getTrees().size();

        return currentTreeCount + 1 <= maxAllowedTrees;
    }
}
