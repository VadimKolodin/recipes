package ru.study.recipes.data.audit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.study.recipes.data.ingredient.IngredientEntity;

import java.util.List;

@Repository
public class AuditRepository {

    @PersistenceContext
    private EntityManager em;

    public void persist(AuditEntity entity) {
        em.persist(entity);
    }

}
