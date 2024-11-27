package com.spring.Citronix.services.impl;

import com.spring.Citronix.dtos.SaleRequestDTO;
import com.spring.Citronix.dtos.responses.SaleResponseDTO;
import com.spring.Citronix.entities.Harvest;
import com.spring.Citronix.entities.Sale;
import com.spring.Citronix.mappers.GenericEntityResolver;
import com.spring.Citronix.mappers.SaleMapper;
import com.spring.Citronix.repositories.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleServiceImplTest {

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private SaleMapper saleMapper;

    @Mock
    private GenericEntityResolver resolver;

    @InjectMocks
    private SaleServiceImpl saleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSale_shouldCreateSaleAndReturnResponse() {
        SaleRequestDTO requestDTO = new SaleRequestDTO();
        requestDTO.setHarvestId(1L);
        requestDTO.setUnitPrice(10.0);

        Harvest harvest = new Harvest();
        harvest.setTotalQuantity(100.0);

        Sale sale = new Sale();
        sale.setUnitPrice(10.0);
        sale.setTotalRevenue(1000.0);

        Sale savedSale = new Sale();
        savedSale.setTotalRevenue(1000.0);

        SaleResponseDTO responseDTO = new SaleResponseDTO();
        responseDTO.setTotalRevenue(1000.0);

        when(resolver.findEntityById(Harvest.class, requestDTO.getHarvestId())).thenReturn(harvest);
        when(saleMapper.toEntity(requestDTO, resolver)).thenReturn(sale);
        when(saleRepository.save(sale)).thenReturn(savedSale);
        when(saleMapper.toDto(savedSale)).thenReturn(responseDTO);

        SaleResponseDTO result = saleService.createSale(requestDTO);

        assertNotNull(result);
        assertEquals(1000.0, result.getTotalRevenue());
        verify(saleRepository, times(1)).save(sale);
    }

    @Test
    void getSalesByHarvestId_shouldReturnListOfSales() {
        Long harvestId = 1L;
        Sale sale = new Sale();
        SaleResponseDTO responseDTO = new SaleResponseDTO();

        when(saleRepository.findByHarvest_Id(harvestId)).thenReturn(List.of(sale));
        when(saleMapper.toDto(sale)).thenReturn(responseDTO);

        List<SaleResponseDTO> result = saleService.getSalesByHarvestId(harvestId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(saleRepository, times(1)).findByHarvest_Id(harvestId);
    }

    @Test
    void getSalesByHarvestId_shouldReturnEmptyListIfNoSales() {
        Long harvestId = 1L;

        when(saleRepository.findByHarvest_Id(harvestId)).thenReturn(Collections.emptyList());

        List<SaleResponseDTO> result = saleService.getSalesByHarvestId(harvestId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(saleRepository, times(1)).findByHarvest_Id(harvestId);
    }

    @Test
    void getSaleById_shouldReturnSaleIfExists() {
        Long saleId = 1L;
        Sale sale = new Sale();
        SaleResponseDTO responseDTO = new SaleResponseDTO();

        when(saleRepository.findById(saleId)).thenReturn(Optional.of(sale));
        when(saleMapper.toDto(sale)).thenReturn(responseDTO);

        SaleResponseDTO result = saleService.getSaleById(saleId);

        assertNotNull(result);
        verify(saleRepository, times(1)).findById(saleId);
    }

    @Test
    void getSaleById_shouldThrowExceptionIfNotFound() {
        Long saleId = 1L;

        when(saleRepository.findById(saleId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> saleService.getSaleById(saleId));
        verify(saleRepository, times(1)).findById(saleId);
    }

    @Test
    void deleteSale_shouldDeleteSaleById() {
        Long saleId = 1L;

        doNothing().when(saleRepository).deleteById(saleId);

        saleService.deleteSale(saleId);

        verify(saleRepository, times(1)).deleteById(saleId);
    }

    @Test
    void updateSale_shouldUpdateFieldsAndReturnResponse() {
        Long saleId = 1L;
        SaleRequestDTO requestDTO = new SaleRequestDTO();
        requestDTO.setDate(LocalDate.parse("2024-11-27"));
        requestDTO.setClient("New Client");

        Sale existingSale = new Sale();
        Sale updatedSale = new Sale();
        SaleResponseDTO responseDTO = new SaleResponseDTO();

        when(saleRepository.findById(saleId)).thenReturn(Optional.of(existingSale));
        when(saleRepository.save(existingSale)).thenReturn(updatedSale);
        when(saleMapper.toDto(updatedSale)).thenReturn(responseDTO);

        SaleResponseDTO result = saleService.updateSale(saleId, requestDTO);

        assertNotNull(result);
        verify(saleRepository, times(1)).save(existingSale);
    }

    @Test
    void updateSale_shouldThrowExceptionIfSaleNotFound() {
        Long saleId = 1L;
        SaleRequestDTO requestDTO = new SaleRequestDTO();

        when(saleRepository.findById(saleId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> saleService.updateSale(saleId, requestDTO));
        verify(saleRepository, times(1)).findById(saleId);
    }
}
