package com.spring.Citronix.repositories;

import com.spring.Citronix.entities.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {
}
