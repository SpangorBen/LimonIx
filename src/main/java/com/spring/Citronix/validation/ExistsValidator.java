package com.spring.Citronix.validation;

import com.spring.Citronix.annotations.Exists;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;


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
        if (value == null) return false;

        Query query = entityManager.createQuery(
                "SELECT COUNT(e) FROM " + entity.getSimpleName() + " e WHERE e." + field + " = :value");
        query.setParameter("value", value);

        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
}
