package com.spring.Citronix.controllers;

import com.spring.Citronix.dtos.TreeRequestDTO;
import com.spring.Citronix.dtos.responses.TreeResponseDTO;
import com.spring.Citronix.services.TreeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trees")
@RequiredArgsConstructor
public class TreeController {

    private final TreeService treeService;

    @PostMapping("/create")
    public ResponseEntity<TreeResponseDTO> createTree(@RequestBody @Valid TreeRequestDTO treeRequestDTO) {
        return ResponseEntity.ok(treeService.createTree(treeRequestDTO));
    }

    @PutMapping("/update/{treeId}")
    public ResponseEntity<TreeResponseDTO> updateTree(@PathVariable Long treeId, @RequestBody TreeRequestDTO treeRequestDTO) {
        return ResponseEntity.ok(treeService.updateTree(treeId, treeRequestDTO));
    }

    @GetMapping("/find/{treeId}")
    public ResponseEntity<TreeResponseDTO> getTree(@PathVariable Long treeId) {
        return ResponseEntity.ok(treeService.getTree(treeId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TreeResponseDTO>> getAllTrees() {
        return ResponseEntity.ok(treeService.getAllTrees());
    }

    @DeleteMapping("/delete/{treeId}")
    public ResponseEntity<Void> deleteTree(@PathVariable Long treeId) {
        treeService.deleteTree(treeId);
        return ResponseEntity.noContent().build();
    }
}
