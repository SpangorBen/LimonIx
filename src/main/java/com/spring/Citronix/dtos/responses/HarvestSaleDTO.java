package com.spring.Citronix.dtos.responses;


import lombok.Data;

import java.time.LocalDate;

@Data
public class HarvestSaleDTO {

    private Long harvestId;
    private LocalDate date;
    private String season;
    private Long fieldId;
    private Double totalQuantity;
}
