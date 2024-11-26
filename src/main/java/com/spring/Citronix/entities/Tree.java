package com.spring.Citronix.entities;

import com.spring.Citronix.entities.enums.TreeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long treeId;

    @Temporal(TemporalType.DATE)
    private LocalDate plantationDate;

    @Transient
    private Integer age;

//    @Enumerated(EnumType.STRING)
    @Transient
    private TreeStatus status;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HarvestDetail> harvestDetails;
}
