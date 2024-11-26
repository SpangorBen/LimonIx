package com.spring.Citronix.services.impl;

import com.spring.Citronix.dtos.HarvestRequestDTO;
import com.spring.Citronix.dtos.responses.HarvestResponseDTO;
import com.spring.Citronix.entities.Harvest;
import com.spring.Citronix.entities.HarvestDetail;
import com.spring.Citronix.entities.Tree;
import com.spring.Citronix.entities.enums.Season;
import com.spring.Citronix.exceptions.ResourceNotFoundException;
import com.spring.Citronix.mappers.GenericEntityResolver;
import com.spring.Citronix.mappers.HarvestMapper;
import com.spring.Citronix.repositories.HarvestRepository;
import com.spring.Citronix.services.HarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final HarvestMapper harvestMapper;
    private final GenericEntityResolver resolver;

    @Override
    public HarvestResponseDTO createHarvest(HarvestRequestDTO harvestRequestDTO) {
        Harvest harvest = harvestMapper.toEntity(harvestRequestDTO, resolver);

        double totalQuantity = harvest.getHarvestDetails().stream()
                .mapToDouble(HarvestDetail::getQuantity)
                .sum();
        harvest.setTotalQuantity(totalQuantity);

        for (HarvestDetail detail : harvest.getHarvestDetails()) {
            detail.setHarvest(harvest);
        }

        harvest = harvestRepository.save(harvest);
        return harvestMapper.toDto(harvest);
    }

    @Override
    public HarvestResponseDTO getHarvestById(Long harvestId) {
        Harvest harvest = harvestRepository.findById(harvestId)
                .orElseThrow(() -> new RuntimeException("Harvest with ID " + harvestId + " not found."));
        return harvestMapper.toDto(harvest);
    }

    @Override
    public List<HarvestResponseDTO> getAllHarvests() {
        List<Harvest> harvests = harvestRepository.findAll();
        return harvests.stream()
                .map(harvestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HarvestResponseDTO updateHarvest(Long id, HarvestRequestDTO harvestRequestDTO) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Harvest not found"));

        if (harvestRequestDTO.getDate() != null) {
            harvest.setDate(harvestRequestDTO.getDate());
        }

        if (harvestRequestDTO.getSeason() != null) {
            harvest.setSeason(Season.valueOf(harvestRequestDTO.getSeason().toUpperCase()));
        }

        harvestRepository.save(harvest);

        return harvestMapper.toDto(harvest);
    }

    @Override
    public void deleteHarvest(Long harvestId) {
        Harvest harvest = harvestRepository.findById(harvestId)
                .orElseThrow(() -> new RuntimeException("Harvest with ID " + harvestId + " not found."));
        harvestRepository.delete(harvest);
    }

}
