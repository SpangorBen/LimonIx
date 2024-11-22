package com.spring.Citronix.services;

import com.spring.Citronix.dtos.FieldRequestDTO;
import com.spring.Citronix.dtos.responses.FieldResponseDTO;

import java.util.List;

public interface FieldService {
    FieldResponseDTO createField(FieldRequestDTO fieldRequestDTO);
    FieldResponseDTO updateField(Long id, FieldRequestDTO fieldRequestDTO);
    void deleteField(Long id);
    FieldResponseDTO getField(Long id);
    List<FieldResponseDTO> getAllFieldsByFarmId(Long farmId);
}
