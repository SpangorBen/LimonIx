package com.spring.Citronix.mappers;

import com.spring.Citronix.dtos.SaleRequestDTO;
import com.spring.Citronix.dtos.responses.SaleResponseDTO;
import com.spring.Citronix.entities.Harvest;
import com.spring.Citronix.entities.Sale;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    SaleResponseDTO toDto(Sale sale);

    @Mapping(target = "harvest", source = "harvestId", qualifiedByName = "resolveHarvest")
    Sale toEntity(SaleRequestDTO saleRequestDTO, @Context GenericEntityResolver resolver);

    @Named("resolveHarvest")
    default Harvest resolveHarvest(Long harvestId, @Context GenericEntityResolver resolver) {
        return resolver.findEntityById(Harvest.class, harvestId);
    }
}
