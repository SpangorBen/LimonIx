package com.spring.Citronix.dtos;

import com.spring.Citronix.annotations.Exists;
import com.spring.Citronix.annotations.ValidPlantationDate;
import com.spring.Citronix.annotations.ValidTreeDensity;
import com.spring.Citronix.entities.Field;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
@ValidTreeDensity
public class TreeRequestDTO {

    @NotNull(message = "Plantation date is required")
    @Past(message = "Plantation date must be in the past")
    @ValidPlantationDate
    private LocalDate plantationDate;

    @NotNull(message = "fieldId is required")
    @Exists(entity = Field.class, message = "Field does not exist")
    private Long fieldId;
}
