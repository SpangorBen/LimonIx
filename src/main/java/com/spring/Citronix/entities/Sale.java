package com.spring.Citronix.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Double totalRevenue;

    private String client;

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;
}
