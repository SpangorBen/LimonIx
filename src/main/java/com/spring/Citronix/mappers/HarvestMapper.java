package com.spring.Citronix.mappers;

import com.spring.Citronix.dtos.HarvestRequestDTO;
import com.spring.Citronix.dtos.responses.HarvestDetailResponseDTO;
import com.spring.Citronix.dtos.responses.HarvestResponseDTO;
import com.spring.Citronix.entities.Field;
import com.spring.Citronix.entities.Harvest;
import com.spring.Citronix.entities.HarvestDetail;
import com.spring.Citronix.entities.Tree;
import com.spring.Citronix.entities.enums.Season;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface HarvestMapper {

    @Mapping(target = "harvestId", source = "id")
    @Mapping(target = "fieldId", source = "field.fieldId")
    @Mapping(target = "harvestDetails", source = "harvestDetails")
    HarvestResponseDTO toDto(Harvest harvest);

    @Mapping(target = "treeId", source = "tree.treeId")
    @Mapping(target = "age", source = "tree.age")
    HarvestDetailResponseDTO toDto(HarvestDetail harvestDetail);

    @Mapping(target = "totalQuantity", ignore = true)
    @Mapping(target = "field", source = "fieldId", qualifiedByName = "resolveField")
    @Mapping(target = "harvestDetails", source = "fieldId", qualifiedByName = "mapHarvestDetails")
    @Mapping(target = "season", expression = "java(com.spring.Citronix.entities.enums.Season.valueOf(harvestRequestDTO.getSeason().toUpperCase()))")
    Harvest toEntity(HarvestRequestDTO harvestRequestDTO, @Context GenericEntityResolver resolver);

    @Named("resolveField")
    default Field resolveField(Long fieldId, @Context GenericEntityResolver resolver) {
        return resolver.findEntityById(Field.class, fieldId);
    }

    @Named("resolveSeason")
    default Season resolveSeason(String season) {
        try {
            return Season.valueOf(season.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid season: " + season);
        }
    }

    @Named("mapHarvestDetails")
    default List<HarvestDetail> mapHarvestDetails(Long fieldId, @Context GenericEntityResolver resolver) {
        Field field = resolver.findEntityById(Field.class, fieldId);
        if (field == null || field.getTrees() == null) return List.of();

        return field.getTrees().stream()
                .map(tree -> {
                    if (tree.getPlantationDate() != null) {
                        int age = Period.between(tree.getPlantationDate(), LocalDate.now()).getYears();
                        tree.setAge(age);
                    }
                    return HarvestDetail.builder()
                            .tree(tree)
                            .quantity(calculateTreeProductivity(tree))
                            .build();
                })
                .collect(Collectors.toList());
    }

    default Double calculateTreeProductivity(Tree tree) {
        if (tree == null || tree.getAge() == null) return 0.0;

        int age = tree.getAge();
        if (age < 3) return 2.5;
        if (age <= 10) return 12.0;
        if (age <= 20) return 20.0;

        return 0.0;
    }
}
