package com.spring.Citronix.dtos.responses;

import com.spring.Citronix.dtos.FarmDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldResponseDTO {
    private Long fieldId;
    private String name;
    private Double area;
    private FarmDTO farm;

}
