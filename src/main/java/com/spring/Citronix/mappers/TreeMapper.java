package com.spring.Citronix.mappers;

import com.spring.Citronix.dtos.TreeRequestDTO;
import com.spring.Citronix.dtos.responses.TreeResponseDTO;
import com.spring.Citronix.entities.Field;
import com.spring.Citronix.entities.Tree;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreeMapper {

//    @Mapping(source = "field", target = "field")
    TreeResponseDTO toDto(Tree tree);

    @Mapping(target = "field", source = "fieldId", qualifiedByName = "resolveField")
    Tree toEntity(TreeRequestDTO treeRequestDTO, @Context GenericEntityResolver resolver);


    @Named("resolveField")
    default Field resolveField(Long fieldId, @Context GenericEntityResolver resolver) {
        return resolver.findEntityById(Field.class, fieldId);
    }

    List<TreeResponseDTO> toDtoList(List<Tree> trees);
}
