package com.spring.Citronix.services.impl;

import com.spring.Citronix.dtos.FarmDTO;
import com.spring.Citronix.entities.Farm;
import com.spring.Citronix.mappers.FarmMapper;
import com.spring.Citronix.repositories.FarmRepository;
import com.spring.Citronix.services.FarmService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;
    private final EntityManager em;

    @Override
    public FarmDTO saveFarm(FarmDTO farmDTO) {
        Farm farm = farmMapper.toEntity(farmDTO);
        Farm savedFarm = farmRepository.save(farm);
        return farmMapper.toDTO(savedFarm);
    }

    @Override
    public FarmDTO updateFarm(Long farmId, FarmDTO farmDTO) {
        Optional<Farm> farmOptional = farmRepository.findById(farmId);

        if (farmOptional.isEmpty()) {
            return null;
        }

        Farm farm = farmOptional.get();
        if (farmDTO.getFarmName() != null) {
            farm.setFarmName(farmDTO.getFarmName());
        }
        if (farmDTO.getLocation() != null) {
            farm.setLocation(farmDTO.getLocation());
        }
        if (farmDTO.getArea() != null) {
            farm.setArea(farmDTO.getArea());
        }

        Farm updatedFarm = farmRepository.save(farm);
        return farmMapper.toDTO(updatedFarm);
    }

    @Override
    public Optional<FarmDTO> findFarmById(Long id) {
         Farm farm = farmRepository.findById(id).orElse(null);
         return Optional.ofNullable(farmMapper.toDTO(farm));
    }

    @Override
    public List<FarmDTO> findAllFarms() {
         return farmRepository.findAll().stream().map(farmMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteFarmById(Long id) {
        if (!farmRepository.existsById(id)) {
            throw new EntityNotFoundException("Farm with id " + id + " not found");
        }
        farmRepository.deleteById(id);
    }

    @Override
    public List<FarmDTO> searchFarms(String name, String location, LocalDate creationDate) {
        return farmRepository.searchFarms(name, location, creationDate).stream().map(farmMapper::toDTO).collect(Collectors.toList());
    }

}
