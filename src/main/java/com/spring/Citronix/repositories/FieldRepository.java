package com.spring.Citronix.repositories;

import com.spring.Citronix.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findAllByFarmFarmId(Long farmId);
}
