package com.spring.Citronix.services.impl;

import com.spring.Citronix.dtos.HarvestRequestDTO;
import com.spring.Citronix.dtos.responses.HarvestResponseDTO;
import com.spring.Citronix.entities.Harvest;
import com.spring.Citronix.entities.HarvestDetail;
import com.spring.Citronix.entities.enums.Season;
import com.spring.Citronix.exceptions.ResourceNotFoundException;
import com.spring.Citronix.mappers.GenericEntityResolver;
import com.spring.Citronix.mappers.HarvestMapper;
import com.spring.Citronix.repositories.HarvestRepository;
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

class HarvestServiceImplTest {

    @Mock
    private HarvestRepository harvestRepository;

    @Mock
    private HarvestMapper harvestMapper;

    @Mock
    private GenericEntityResolver resolver;

    @InjectMocks
    private HarvestServiceImpl harvestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createHarvest_shouldSaveAndReturnHarvest() {
        HarvestRequestDTO requestDTO = new HarvestRequestDTO();
        requestDTO.setSeason("SUMMER");

        Harvest harvest = new Harvest();
        harvest.setSeason(Season.SUMMER);
        harvest.setHarvestDetails(Collections.singletonList(new HarvestDetail(null, 10.0, null, null)));

        HarvestResponseDTO responseDTO = new HarvestResponseDTO();
        responseDTO.setTotalQuantity(10.0);

        when(harvestMapper.toEntity(requestDTO, resolver)).thenReturn(harvest);
        when(harvestRepository.save(harvest)).thenReturn(harvest);
        when(harvestMapper.toDto(harvest)).thenReturn(responseDTO);

        HarvestResponseDTO result = harvestService.createHarvest(requestDTO);

        assertNotNull(result);
        assertEquals(10.0, result.getTotalQuantity());
        verify(harvestRepository, times(1)).save(harvest);
    }

    @Test
    void getHarvestById_shouldReturnHarvest() {
        Long harvestId = 1L;
        Harvest harvest = new Harvest();
        harvest.setId(harvestId);

        HarvestResponseDTO responseDTO = new HarvestResponseDTO();
        responseDTO.setHarvestId(harvestId);

        when(harvestRepository.findById(harvestId)).thenReturn(Optional.of(harvest));
        when(harvestMapper.toDto(harvest)).thenReturn(responseDTO);

        HarvestResponseDTO result = harvestService.getHarvestById(harvestId);

        assertNotNull(result);
        assertEquals(harvestId, result.getHarvestId());
    }

    @Test
    void getHarvestById_shouldThrowExceptionIfNotFound() {
        Long harvestId = 1L;
        when(harvestRepository.findById(harvestId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> harvestService.getHarvestById(harvestId));
        assertEquals("Harvest with ID 1 not found.", exception.getMessage());
    }

    @Test
    void getAllHarvests_shouldReturnListOfHarvests() {
        Harvest harvest1 = new Harvest();
        harvest1.setId(1L);

        Harvest harvest2 = new Harvest();
        harvest2.setId(2L);

        List<Harvest> harvests = List.of(harvest1, harvest2);

        HarvestResponseDTO responseDTO1 = new HarvestResponseDTO();
        HarvestResponseDTO responseDTO2 = new HarvestResponseDTO();

        when(harvestRepository.findAll()).thenReturn(harvests);
        when(harvestMapper.toDto(harvest1)).thenReturn(responseDTO1);
        when(harvestMapper.toDto(harvest2)).thenReturn(responseDTO2);

        List<HarvestResponseDTO> result = harvestService.getAllHarvests();

        assertEquals(2, result.size());
        verify(harvestRepository, times(1)).findAll();
    }



}
