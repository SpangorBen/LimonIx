package com.spring.Citronix.repositories;

import com.spring.Citronix.entities.Farm;

import java.time.LocalDate;
import java.util.List;

public interface FarmRepositoryCustom {
    List<Farm> searchFarms(String name, String location, LocalDate creationDate);
}
