package com.spring.Citronix.repositories.impl;

import com.spring.Citronix.entities.Farm;
import com.spring.Citronix.repositories.FarmRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FarmRepositoryCustomImpl implements FarmRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Farm> searchFarms(String name, String location, LocalDate creationDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Farm> cq = cb.createQuery(Farm.class);
        Root<Farm> farm = cq.from(Farm.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(farm.get("farmName"), "%" + name + "%"));
        }
        if (location != null && !location.isEmpty()) {
            predicates.add(cb.like(farm.get("location"), "%" + location + "%"));
        }
        if (creationDate != null) {
            predicates.add(cb.equal(farm.get("creationDate"), creationDate));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }
}
