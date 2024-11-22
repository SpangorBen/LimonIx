package com.spring.Citronix.services.impl;

import com.spring.Citronix.dtos.FieldRequestDTO;
import com.spring.Citronix.dtos.responses.FieldResponseDTO;
import com.spring.Citronix.entities.Field;
import com.spring.Citronix.mappers.FieldMapper;
import com.spring.Citronix.mappers.GenericEntityResolver;
import com.spring.Citronix.repositories.FieldRepository;
import com.spring.Citronix.services.FieldService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final FieldMapper fieldMapper;
    private final GenericEntityResolver resolver;

    @Override
    public FieldResponseDTO createField(FieldRequestDTO fieldRequestDTO) {
        Field field = fieldMapper.toEntity(fieldRequestDTO, resolver);
        field = fieldRepository.save(field);
        return fieldMapper.toDto(field);
    }

    @Override
    public FieldResponseDTO updateField(Long id, FieldRequestDTO fieldRequestDTO) {
        Optional<Field> fieldOptional = fieldRepository.findById(id);

        if (fieldOptional.isEmpty()) {
            throw new RuntimeException("Field not found");
        }

        Field field = fieldOptional.get();
        if (fieldRequestDTO.getName() != null) {
            field.setName(fieldRequestDTO.getName());
        }
        if (fieldRequestDTO.getArea() != null) {
            field.setArea(fieldRequestDTO.getArea());
        }
        field = fieldRepository.save(field);
        return fieldMapper.toDto(field);
    }

    @Override
    public void deleteField(Long fieldId) {
        if (!fieldRepository.existsById(fieldId)) {
            throw new EntityNotFoundException("Field not found");
        }
        fieldRepository.deleteById(fieldId);
    }

    @Override
    public FieldResponseDTO getField(Long fieldId) {
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new EntityNotFoundException("Field not found"));
        return fieldMapper.toDto(field);
    }

    @Override
    public List<FieldResponseDTO> getAllFieldsByFarmId(Long farmId) {
        return fieldRepository.findAllByFarmFarmId(farmId).stream()
                .map(fieldMapper::toDto)
                .toList();
    }
}
