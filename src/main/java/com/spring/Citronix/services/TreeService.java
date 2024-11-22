package com.spring.Citronix.services;

import com.spring.Citronix.dtos.TreeRequestDTO;
import com.spring.Citronix.dtos.responses.TreeResponseDTO;

import java.util.List;

public interface TreeService {
    TreeResponseDTO createTree(TreeRequestDTO treeRequestDTO);
    TreeResponseDTO updateTree(Long treeId, TreeRequestDTO treeRequestDTO);
    TreeResponseDTO getTree(Long treeId);
    List<TreeResponseDTO> getAllTrees();
    void deleteTree(Long treeId);
}
