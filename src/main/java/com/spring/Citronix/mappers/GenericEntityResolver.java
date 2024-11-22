package com.spring.Citronix.mappers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class GenericEntityResolver {

    @PersistenceContext
    private EntityManager em;

    public <T> T findEntityById(Class<T> clazz, Long id) {
        if (id == null) {
            return null;
        }

        return em.find(clazz, id);
    }
}
