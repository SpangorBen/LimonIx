package com.spring.Citronix.controllers;

import com.spring.Citronix.dtos.FieldRequestDTO;
import com.spring.Citronix.dtos.responses.FieldResponseDTO;
import com.spring.Citronix.services.FieldService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
@RequiredArgsConstructor
public class FieldController {

    private final FieldService fieldService;

    @PostMapping("/create")
    public ResponseEntity<FieldResponseDTO> createField(@Valid @RequestBody FieldRequestDTO fieldRequestDTO) {
        FieldResponseDTO fieldResponseDTO = fieldService.createField(fieldRequestDTO);
        return ResponseEntity.ok(fieldResponseDTO);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<FieldResponseDTO> getFieldById(@PathVariable Long id) {
        FieldResponseDTO fieldDTO = fieldService.getField(id);
        return new ResponseEntity<>(fieldDTO, HttpStatus.OK);
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<FieldResponseDTO>> getAllFields(@PathVariable Long farmId) {
        List<FieldResponseDTO> fields = fieldService.getAllFieldsByFarmId(farmId);
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FieldResponseDTO> updateField(@PathVariable Long id, @RequestBody FieldRequestDTO fieldRequestDTO) {
        FieldResponseDTO updatedField = fieldService.updateField(id, fieldRequestDTO);
        return new ResponseEntity<>(updatedField, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        fieldService.deleteField(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
