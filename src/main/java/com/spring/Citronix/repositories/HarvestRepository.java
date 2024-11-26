package com.spring.Citronix.repositories;

import com.spring.Citronix.entities.Harvest;
import com.spring.Citronix.entities.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {
    @Query("SELECT COUNT(h) > 0 FROM Harvest h WHERE h.field.fieldId = :fieldId AND h.season = :season")
    boolean existsByFieldIdAndSeason(@Param("fieldId") Long fieldId, @Param("season") Season season);

}
