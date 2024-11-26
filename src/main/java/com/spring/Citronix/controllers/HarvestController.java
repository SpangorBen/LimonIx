package com.spring.Citronix.controllers;

import com.spring.Citronix.dtos.HarvestRequestDTO;
import com.spring.Citronix.dtos.responses.HarvestResponseDTO;
import com.spring.Citronix.services.HarvestService;
import com.spring.Citronix.validation.groups.HarvestCreate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/harvests")
@RequiredArgsConstructor
public class HarvestController {

    private final HarvestService harvestService;

    @PostMapping("/create")
    public ResponseEntity<HarvestResponseDTO> createHarvest(@RequestBody @Valid @Validated(HarvestCreate.class) HarvestRequestDTO harvestRequestDTO) {
        return ResponseEntity.ok(harvestService.createHarvest(harvestRequestDTO));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<HarvestResponseDTO> getHarvestById(@PathVariable Long id) {
        HarvestResponseDTO harvestResponseDTO = harvestService.getHarvestById(id);
        return new ResponseEntity<>(harvestResponseDTO, HttpStatus.OK);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<HarvestResponseDTO>> getAllHarvests() {
        List<HarvestResponseDTO> harvests = harvestService.getAllHarvests();
        return new ResponseEntity<>(harvests, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HarvestResponseDTO> updateHarvest(
            @PathVariable Long id,
            @RequestBody @Valid HarvestRequestDTO harvestRequestDTO) {
        HarvestResponseDTO updatedHarvest = harvestService.updateHarvest(id, harvestRequestDTO);
        return new ResponseEntity<>(updatedHarvest, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHarvest(@PathVariable Long id) {
        harvestService.deleteHarvest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
