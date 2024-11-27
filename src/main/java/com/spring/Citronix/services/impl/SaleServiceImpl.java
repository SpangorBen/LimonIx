package com.spring.Citronix.services.impl;

import com.spring.Citronix.dtos.SaleRequestDTO;
import com.spring.Citronix.dtos.responses.SaleResponseDTO;
import com.spring.Citronix.entities.Harvest;
import com.spring.Citronix.entities.Sale;
import com.spring.Citronix.mappers.GenericEntityResolver;
import com.spring.Citronix.mappers.SaleMapper;
import com.spring.Citronix.repositories.SaleRepository;
import com.spring.Citronix.services.SaleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final GenericEntityResolver resolver;


    public SaleResponseDTO createSale(SaleRequestDTO saleRequestDTO) {
        Harvest harvest = resolver.findEntityById(Harvest.class, saleRequestDTO.getHarvestId());

        Sale sale = saleMapper.toEntity(saleRequestDTO, resolver);
        sale.setTotalRevenue(harvest.getTotalQuantity() * sale.getUnitPrice());
        Sale savedSale = saleRepository.save(sale);

        return saleMapper.toDto(savedSale);
    }

    @Override
    public List<SaleResponseDTO> getSalesByHarvestId(Long harvestId) {
        return saleRepository.findByHarvest_Id(harvestId).stream()
                .map(saleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SaleResponseDTO getSaleById(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new EntityNotFoundException("Sale with id " + saleId + " not found"));

        return saleMapper.toDto(sale);
    }

    @Override
    public void deleteSale(Long saleId) {
        saleRepository.deleteById(saleId);
    }

    @Override
    public SaleResponseDTO updateSale(Long saleId, SaleRequestDTO saleRequestDTO) {

        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new EntityNotFoundException("Sale with ID " + saleId + " not found"));

        if(saleRequestDTO.getDate() != null) {
            sale.setDate(saleRequestDTO.getDate());
        }

        if (saleRequestDTO.getClient() != null) {
            sale.setClient(saleRequestDTO.getClient());
        }

        Sale updatedSale = saleRepository.save(sale);

        return saleMapper.toDto(updatedSale);
    }
}
