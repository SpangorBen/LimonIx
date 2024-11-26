package com.spring.Citronix.repositories;

import com.spring.Citronix.entities.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TreeRepository extends JpaRepository<Tree, Long> {
    @Query("SELECT t.id FROM Tree t WHERE t.field.id = :fieldId")
    List<Long> findTreeIdsByFieldId(@Param("fieldId") Long fieldId);
}
