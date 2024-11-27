package com.spring.Citronix.services;

import com.spring.Citronix.dtos.SaleRequestDTO;
import com.spring.Citronix.dtos.responses.SaleResponseDTO;

import java.util.List;

public interface SaleService {
    SaleResponseDTO createSale(SaleRequestDTO saleRequestDTO);
    List<SaleResponseDTO> getSalesByHarvestId(Long harvestId);
    SaleResponseDTO getSaleById(Long saleId);
    void deleteSale(Long saleId);
    SaleResponseDTO updateSale(Long saleId, SaleRequestDTO saleRequestDTO);
}
