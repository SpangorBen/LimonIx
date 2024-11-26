package com.spring.Citronix.validation;

import com.spring.Citronix.annotations.Exists;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class ExistsValidator implements ConstraintValidator<Exists, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> entity;
    private String field;


    @Override
    public void initialize(Exists constraintAnnotation) {
        this.entity = constraintAnnotation.entity();
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return true;

        if (value instanceof Collection) {
            return ((Collection<?>) value).stream()
                    .allMatch(this::doesEntityExist);
        }

        return doesEntityExist(value);
    }

    private boolean doesEntityExist(Object value) {
        String query = String.format("SELECT COUNT(e) FROM %s e WHERE e.%s = :value",
                entity.getSimpleName(), field);
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("value", value)
                .getSingleResult();
        return count != null && count > 0;
    }
}
