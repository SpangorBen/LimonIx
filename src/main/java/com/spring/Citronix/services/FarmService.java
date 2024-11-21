package com.spring.Citronix.services;

import com.spring.Citronix.dtos.FarmDTO;
import com.spring.Citronix.entities.Farm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FarmService {
    FarmDTO saveFarm(FarmDTO farmDTO);
    FarmDTO updateFarm(Long farmId, FarmDTO farmDTO);
    Optional<FarmDTO> findFarmById(Long id);
    List<FarmDTO> findAllFarms();
    void deleteFarmById(Long id);
    List<FarmDTO> searchFarms(String name, String location, LocalDate creationDate);
}
