package com.spring.Citronix.controllers;

import com.spring.Citronix.dtos.FarmDTO;
import com.spring.Citronix.services.FarmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/farm")
@RequiredArgsConstructor
public class FarmController {

    private final FarmService farmService;


    @GetMapping("/all")
    public ResponseEntity<?> getAllFarms() {
        return ResponseEntity.ok(farmService.findAllFarms());
    }

    @GetMapping("/find/{farmId}")
    public ResponseEntity<FarmDTO> findFarmById(@PathVariable Long farmId) {
        return ResponseEntity.ok(farmService.findFarmById(farmId).orElse(null));
    }

    @PostMapping("/save")
    public ResponseEntity<FarmDTO> saveFarm(@RequestBody @Valid FarmDTO farmDTO) {
        return ResponseEntity.ok(farmService.saveFarm(farmDTO));
    }

    @PutMapping("/update/{farmId}")
    public ResponseEntity<FarmDTO> updateFarm(@PathVariable Long farmId, @RequestBody FarmDTO farmDTO) {
        return ResponseEntity.ok(farmService.updateFarm(farmId, farmDTO));
    }

    @DeleteMapping("/delete/{farmId}")
    public ResponseEntity<String> deleteFarm(@PathVariable Long farmId) {
        farmService.deleteFarmById(farmId);
        return ResponseEntity.ok("Farm deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFarms(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String location,
                                         @RequestParam(required = false) LocalDate creationDate) {
        List<FarmDTO> farms = farmService.searchFarms(name, location, creationDate);
        return ResponseEntity.ok(farms);
    }
}
