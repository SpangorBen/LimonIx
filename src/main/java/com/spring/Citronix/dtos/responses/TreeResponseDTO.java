package com.spring.Citronix.dtos.responses;

import com.spring.Citronix.entities.Field;
import com.spring.Citronix.entities.enums.TreeStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TreeResponseDTO {
    private Long treeId;
    private LocalDate plantationDate;
    private Integer age;
    private TreeStatus status;
    private FieldResponseDTO field;
}
