package com.spring.Citronix.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FarmDTO {

    private Long farmId;

    @NotBlank(message = "Farm name is required")
    @Size(min = 3, max = 50, message = "Farm name must be between 3 and 50 characters")
    private String farmName;

    @NotBlank(message = "Location is required")
    @Size(min = 3, max = 50, message = "Location must be between 3 and 50 characters")
    private String location;

    @NotNull(message = "Area is required")
    @DecimalMin(value = "0.2", message = "Area must be greater than 0.2")
    private Double area;

    @NotNull(message = "Creation date is required")
    @PastOrPresent(message = "Creation date must be in the past or present")
    private LocalDate creationDate;


}
