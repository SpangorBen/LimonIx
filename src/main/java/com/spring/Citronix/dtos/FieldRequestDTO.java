package com.spring.Citronix.dtos;

import com.spring.Citronix.annotations.Exists;
import com.spring.Citronix.annotations.MaxFieldsPerFarm;
import com.spring.Citronix.annotations.ValidFieldArea;
import com.spring.Citronix.annotations.ValidFieldAreaSum;
import com.spring.Citronix.entities.Farm;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MaxFieldsPerFarm
@ValidFieldArea
@ValidFieldAreaSum
public class FieldRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Area is required")
    @DecimalMin(value = "0.1", message = "Field size must be at least 0.1 hectares")
    private Double area;

    @NotNull(message = "Farm ID is required")
    @Exists(entity = Farm.class, message = "Farm does not exist")
    private Long farmId;

}
