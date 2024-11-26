package com.spring.Citronix.services;

import com.spring.Citronix.dtos.HarvestRequestDTO;
import com.spring.Citronix.dtos.responses.HarvestResponseDTO;

import java.util.List;

public interface HarvestService {
    HarvestResponseDTO createHarvest(HarvestRequestDTO harvestRequestDTO);
    HarvestResponseDTO getHarvestById(Long harvestId);

    List<HarvestResponseDTO> getAllHarvests();

    HarvestResponseDTO updateHarvest(Long harvestId, HarvestRequestDTO harvestRequestDTO);

    void deleteHarvest(Long harvestId);
}
