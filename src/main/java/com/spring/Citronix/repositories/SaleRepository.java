package com.spring.Citronix.repositories;

import com.spring.Citronix.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByHarvest_Id(Long harvestId);
    boolean existsByHarvest_Id(Long harvestId);
}
