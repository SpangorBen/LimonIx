package com.spring.Citronix.dtos;

import com.spring.Citronix.annotations.Exists;
import com.spring.Citronix.annotations.ValidDateSeason;
import com.spring.Citronix.annotations.ValidFieldSeason;
import com.spring.Citronix.entities.Field;
import com.spring.Citronix.validation.HarvestCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
@ValidFieldSeason
@ValidDateSeason
public class HarvestRequestDTO {

    @NotNull(message = "Tree id is required")
    @PastOrPresent(message = "Date must be in the past or present")
    private LocalDate date;

    @NotBlank(message = "Season is required")
    private String season;

    @NotNull(message = "fieldId is required", groups = HarvestCreate.class)
    @Exists(entity = Field.class, message = "Field does not exist")
    private Long fieldId;

}
