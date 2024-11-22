package com.spring.Citronix.services.impl;

import com.spring.Citronix.dtos.TreeRequestDTO;
import com.spring.Citronix.dtos.responses.TreeResponseDTO;
import com.spring.Citronix.entities.Field;
import com.spring.Citronix.entities.Tree;
import com.spring.Citronix.entities.enums.TreeStatus;
import com.spring.Citronix.mappers.GenericEntityResolver;
import com.spring.Citronix.mappers.TreeMapper;
import com.spring.Citronix.repositories.TreeRepository;
import com.spring.Citronix.services.TreeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;
    private final TreeMapper treeMapper;
    private final GenericEntityResolver resolver;

    public TreeResponseDTO createTree(TreeRequestDTO treeRequestDTO) {
        Tree tree = treeMapper.toEntity(treeRequestDTO, resolver);

        LocalDate plantationDate = tree.getPlantationDate();
        if (plantationDate != null) {
            tree.setAge(Period.between(plantationDate, LocalDate.now()).getYears());

            if (tree.getAge() < 3) {
                tree.setStatus(TreeStatus.YOUNG);
            } else if (tree.getAge() <= 10) {
                tree.setStatus(TreeStatus.MATURE);
            } else {
                tree.setStatus(TreeStatus.OLD);
            }
        }

        tree = treeRepository.save(tree);

        return treeMapper.toDto(tree);
    }

    @Override
    public TreeResponseDTO updateTree(Long treeId, TreeRequestDTO treeRequestDTO) {
        Tree tree = treeRepository.findById(treeId).orElseThrow(() -> new EntityNotFoundException("Tree not found"));

        if (treeRequestDTO.getPlantationDate() != null) {
            tree.setPlantationDate(treeRequestDTO.getPlantationDate());
        }
        if (treeRequestDTO.getFieldId() != null) {
            tree.setField(resolver.findEntityById(Field.class, treeRequestDTO.getFieldId()));
        }

        LocalDate plantationDate = tree.getPlantationDate();
        if (plantationDate != null) {
            tree.setAge(Period.between(plantationDate, LocalDate.now()).getYears());

            if (tree.getAge() < 3) {
                tree.setStatus(TreeStatus.YOUNG);
            } else if (tree.getAge() <= 10) {
                tree.setStatus(TreeStatus.MATURE);
            } else {
                tree.setStatus(TreeStatus.OLD);
            }
        }

        tree = treeRepository.save(tree);

        return treeMapper.toDto(tree);
    }

    @Override
    public TreeResponseDTO getTree(Long treeId) {
        Tree tree = treeRepository.findById(treeId).orElseThrow(() -> new EntityNotFoundException("Tree not found"));
        return treeMapper.toDto(tree);
    }

    @Override
    public List<TreeResponseDTO> getAllTrees(){
        List<Tree> trees = treeRepository.findAll();
        return treeMapper.toDtoList(trees);
    }

    @Override
    public void deleteTree(Long treeId){
        if(!treeRepository.existsById(treeId)){
            throw new EntityNotFoundException("Tree with id: " + treeId + " not found");
        }

        treeRepository.deleteById(treeId);
    }
}
