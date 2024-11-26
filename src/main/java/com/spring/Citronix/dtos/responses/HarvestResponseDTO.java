package com.spring.Citronix.dtos.responses;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HarvestResponseDTO {

    private Long harvestId;
    private LocalDate date;
    private String season;
    private Long fieldId;
    private Double totalQuantity;
    private List<HarvestDetailResponseDTO> harvestDetails;
}
