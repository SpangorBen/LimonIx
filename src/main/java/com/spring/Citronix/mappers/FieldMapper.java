package com.spring.Citronix.mappers;

import com.spring.Citronix.dtos.FieldRequestDTO;
import com.spring.Citronix.dtos.responses.FieldResponseDTO;
import com.spring.Citronix.entities.Farm;
import com.spring.Citronix.entities.Field;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface FieldMapper {

//    @Mapping(source = "farm", target = "farm")
    FieldResponseDTO toDto(Field field);

    @Mapping(target = "farm", source = "farmId", qualifiedByName = "resolveFarm")
    Field toEntity(FieldRequestDTO fieldRequestDTO, @Context GenericEntityResolver resolver);

    @Named("resolveFarm")
    default Farm resolveFarm(Long farmId, @Context GenericEntityResolver resolver) {
        return resolver.findEntityById(Farm.class, farmId);
    }
}
