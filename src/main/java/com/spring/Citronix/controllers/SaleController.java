package com.spring.Citronix.controllers;

import com.spring.Citronix.dtos.SaleRequestDTO;
import com.spring.Citronix.dtos.responses.SaleResponseDTO;
import com.spring.Citronix.services.SaleService;
import com.spring.Citronix.validation.SaleCreate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping("/create")
    public ResponseEntity<SaleResponseDTO> createSale(@RequestBody @Valid @Validated(SaleCreate.class) SaleRequestDTO saleRequestDTO) {
        SaleResponseDTO sale = saleService.createSale(saleRequestDTO);
        return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SaleResponseDTO> updateSale(
            @PathVariable Long id, @RequestBody @Valid SaleRequestDTO saleRequestDTO) {
        SaleResponseDTO updatedSale = saleService.updateSale(id, saleRequestDTO);
        return ResponseEntity.ok(updatedSale);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SaleResponseDTO> getSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.getSaleById(id));
    }

    @GetMapping("/harvest/{harvestId}")
    public ResponseEntity<List<SaleResponseDTO>> getSalesByHarvest(@PathVariable Long harvestId) {
        return ResponseEntity.ok(saleService.getSalesByHarvestId(harvestId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}

