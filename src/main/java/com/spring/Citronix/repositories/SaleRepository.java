package com.spring.Citronix.repositories;

import com.spring.Citronix.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
