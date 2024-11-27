package com.spring.Citronix.dtos.responses;


import lombok.Data;

@Data
public class SaleResponseDTO {

    private Long id;
    private String date;
    private Double unitPrice;
    private Double totalRevenue;
    private String client;
    private HarvestSaleDTO harvest;
}
