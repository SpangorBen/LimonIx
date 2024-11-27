package com.spring.Citronix.dtos;

import com.spring.Citronix.annotations.Exists;
import com.spring.Citronix.annotations.UniqueHarvestSale;
import com.spring.Citronix.annotations.ValidSaleDate;
import com.spring.Citronix.entities.Harvest;
import com.spring.Citronix.validation.SaleCreate;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@ValidSaleDate
@UniqueHarvestSale
public class SaleRequestDTO {

    @NotNull(message = "Date cannot be empty")
    @PastOrPresent(message = "Date must be in the past or present")
    private LocalDate date;

    @NotNull(message = "Unit price cannot be empty", groups = SaleCreate.class)
    @Positive(message = "Unit price must be positive")
    private Double unitPrice;

    @NotBlank(message = "Client cannot be empty")
    @Size(max = 100, min = 3)
    private String client;

    @NotNull(message = "Harvest id cannot be empty", groups = SaleCreate.class)
    @Exists(entity = Harvest.class)
    private Long harvestId;
}
