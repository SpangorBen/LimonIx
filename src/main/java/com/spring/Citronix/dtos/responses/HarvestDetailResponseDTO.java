package com.spring.Citronix.dtos.responses;


import lombok.Data;

@Data
public class HarvestDetailResponseDTO {

    private Long treeId;
    private Integer age;
    private Double quantity;
}
