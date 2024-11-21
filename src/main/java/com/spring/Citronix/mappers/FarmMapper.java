package com.spring.Citronix.mappers;

import com.spring.Citronix.dtos.FarmDTO;
import com.spring.Citronix.entities.Farm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FarmMapper {

    @Mapping(target = "farmId", ignore = true)
//    @Mapping(target = "creationDate", ignore = true)
    Farm toEntity(FarmDTO farmDto);
    FarmDTO toDTO(Farm farm);
}
